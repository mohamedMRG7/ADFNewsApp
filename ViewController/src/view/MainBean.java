package view;


import com.prosecution.ucm.utils.ucm.UCMUtilities;

import java.awt.Image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import java.nio.file.Files;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Random;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import javax.el.MethodExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.event.MethodExpressionValueChangeListener;
import javax.faces.event.ValueChangeEvent;

import javax.faces.event.ValueChangeListener;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichLink;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import oracle.jbo.domain.BlobDomain;

import oracle.stellent.ridc.IdcClientException;

import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;


public class MainBean implements Serializable {
    private RichPanelGroupLayout panalGroupComp;
    private RichInputFile input;

    List<RichPanelGroupLayout> containersList = new ArrayList<>();
    List<String> contentDIDList = new ArrayList<>();
    Map<String, String> contentDIDMap = new HashMap<>();
    int tOut = 0;
    private RichPanelGroupLayout previewPanalGroupComp;
    private RichOutputText f;
    private RichImage ds;
    private RichDialog dialog;
    private HtmlGraphicImage imgageBind;
    private String imagePath = "";
    private String fNAme;
    private String binPath = "C:\\oracle\\Middleware1\\jdeveloper\\jdev\\bin\\";
    private String jdevPath =
        "C:\\Users\\Abdelrahman\\AppData\\Roaming\\JDeveloper\\system12.1.3.0.41.140521.1008\\o.j2ee\\drs\\NewsApplication\\ViewControllerWebApp.war\\images\\";
    String title;
    String thumbNail;
    String htmlThumbNail;
    private HtmlInputTextarea inArea;
    UCMUtilities utils;
    private String arabicTitle;
    private String englishDesc;
    private String arabicDesc;
    private RichInputText incasd;
    private RichOutputText thumbNailStateComp;
    AdfFacesContext context=AdfFacesContext.getCurrentInstance();
    public MainBean() {
        try {
            utils = new UCMUtilities("idc://10.3.1.88:4444", "weblogic", "welcome1");
            utils.login();
            
            readBlob();
        } catch (IdcClientException e) {
        }

    }

    public void setDialog(RichDialog dialog) {
        this.dialog = dialog;
    }

    public RichDialog getDialog() {
        return dialog;
    }

    public void setF(RichOutputText f) {
        this.f = f;
    }

    public RichOutputText getF() {
        return f;
    }

    public void setDs(RichImage ds) {
        this.ds = ds;
    }

    public RichImage getDs() {

        return ds;
    }

    public void setImgageBind(HtmlGraphicImage imgageBind) {
        this.imgageBind = imgageBind;
    }

    public HtmlGraphicImage getImgageBind() {


        return imgageBind;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }


    public void setPanalGroupComp(RichPanelGroupLayout panalGroupComp) {
        this.panalGroupComp = panalGroupComp;
    }

    public RichPanelGroupLayout getPanalGroupComp() {
        return panalGroupComp;
    }

    public void setInput(RichInputFile input) {
        this.input = input;
    }

    public RichInputFile getInput() {
        return input;
    }

    public void setThumbNail(String thumbNail) {
        this.thumbNail = thumbNail;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public void uploadThumbnail(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        if (valueChangeEvent.getNewValue() != null) {
            //Get File Object from VC Event
            UploadedFile fileVal = (UploadedFile) valueChangeEvent.getNewValue();

            // uploadFile(fileVal, jdevPath);
            htmlThumbNail = uploadFileToUCM(fileVal);
            getThumbNailStateComp().setVisible(false);
            context.addPartialTarget(getThumbNailStateComp());
            

        }
    }

    public void uploadContentImage(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        if (valueChangeEvent.getNewValue() != null) {
            //Get File Object from VC Event
            UploadedFile fileVal = (UploadedFile) valueChangeEvent.getNewValue();
            //Upload File to path- Return actual server path

            String imageDid = uploadFileToUCM(fileVal);
            contentDIDMap.put(fileVal.getFilename(), imageDid);
            
            
          
            RichOutputText outputText = (RichOutputText) valueChangeEvent.getComponent().getParent().getChildren().get(3);
            outputText.setVisible(false);
            context.addPartialTarget(outputText.getParent());
        }
    }
   
    private String uploadFileToUCM(UploadedFile file) {

        UploadedFile myfile = file;

        String dId = null;
        if (myfile != null) {
            // All uploaded files will be stored in below path
            System.out.println(">>>>filename" + file.getFilename());
            setImagePath(file.getFilename());
            fNAme = file.getFilename();
            InputStream inputStream = null;
            try {
                inputStream = myfile.getInputStream();
                Random r = new Random();
                dId =
                    utils.checkInDocumentReturnDID(myfile.getFilename() + r.nextInt(), getTitle(), "Document", "public",
                                                   inputStream, myfile.getFilename(), new HashMap());

            } catch (Exception e) {
            }

        }
        //Returns the path where file is stored
        return dId;
    }


    public String getUcmURL(String did) {
        String url = null;
        try {
            url = utils.getDocUrlByDid(did);
        } catch (IOException e) {
        } catch (IdcClientException e) {
        }
        return url;
    }

    public void addComponnent(ActionEvent actionEvent) {
        // Add event code here...
        RichPanelGroupLayout container =
            createaddCompPanalGroup(getPanalGroupComp(), RichPanelGroupLayout.LAYOUT_HORIZONTAL);
        containersList.add(container);
        createInputTextComponent(container, Language.ENGLISH);
        createInputTextComponent(container, Language.ARABIC);
        createFileUploadeComp(container);
        createOutPutComp("uploading...",container);
        createRemovePanalComp(container);


    }


    public void removePanal(ActionEvent actionEvent) {
        UIComponent parent = actionEvent.getComponent().getParent();
        containersList.remove(parent);
        getPanalGroupComp().getChildren().remove(parent);
    }

    public void preview(ActionEvent actionEvent) {
        // Add event code here...
        if(checkImagesUploaded())
        showPreview(Language.ENGLISH);
        
        
    }
    public void arabicPreview(ActionEvent actionEvent) {
        // Add event code here...
        if(checkImagesUploaded())
        showPreview(Language.ARABIC);
        
    }

    public void showPreview(Language lang) {
        fNAme = "";
        String contesnt = "";
        int i = 0;
        String splitter = "03213216523c";
        String selectedTitle="";
        String selectedDesc="";
        int langComp;
        
        if (lang == Language.ENGLISH){
            langComp = 0;
            selectedTitle=getTitle();
            selectedDesc=getEnglishDesc();
        }else{
            langComp = 1;
            selectedTitle=getArabicTitle();
            selectedDesc=getArabicDesc();
        }
        for (RichPanelGroupLayout container : containersList) {

            RichInputFile imageComp = (RichInputFile) containersList.get(i).getChildren().get(2);

            if (imageComp.getValue() != null) {
                String imageName = ((UploadedFile) imageComp.getValue()).getFilename();
                String imgDID = contentDIDMap.get(imageName);
                String url = getUcmURL(imgDID);
                fNAme += url + ",";
            } else {
                fNAme += "NA,";
            }
           
            RichInputText inText = (RichInputText) container.getChildren().get(langComp);
            if (inText.getValue() != null && !inText.getValue().equals(""))
                contesnt += inText.getValue().toString() + splitter;
            else
                contesnt += "NA" + splitter;
            i++;
        }

        
        
        System.out.println(selectedDesc);
        openNewWindow(fNAme, contesnt, selectedTitle, getUcmURL(htmlThumbNail),selectedDesc);

    }

    //
    public void addComponent(UIComponent parentUIComponent, UIComponent childUIComponent) {
        parentUIComponent.getChildren().add(childUIComponent);
        AdfFacesContext.getCurrentInstance().addPartialTarget(parentUIComponent);
    }


    public RichInputText createInputTextComponent(RichPanelGroupLayout layOut, Language lang) {
        RichInputText richInputText = new RichInputText();
        Random r = new Random();
        richInputText.setId("rit1" + r.nextInt());
        richInputText.setLabel(lang.name());
        richInputText.setColumns(40);
        richInputText.setRows(10);
        richInputText.setInlineStyle("margin:20px;");

        //richInputText.setContentStyle("font-weight:bold;color:green");
        addComponent(layOut, richInputText);
        return richInputText;
    }

    public RichInputFile createFileUploadeComp(RichPanelGroupLayout layOut) {
        RichInputFile richInputFile = new RichInputFile();
        Random r = new Random();
        richInputFile.setId("rit1" + r.nextInt());
        richInputFile.setLabel("Image");
        richInputFile.setAutoSubmit(true);
        richInputFile.addValueChangeListener(resolveValueChangeListener("#{MainBean.uploadContentImage}"));
        richInputFile.setInlineStyle("margin:20px;");
        //richInputText.setContentStyle("font-weight:bold;color:green");
        addComponent(layOut, richInputFile);
        return richInputFile;
    }

    public RichLink createRemovePanalComp(RichPanelGroupLayout layOut) {
        RichLink richLink = new RichLink();
        Random r = new Random();
        richLink.setId("rit1" + r.nextInt());
        richLink.setText("Remove");
        richLink.setInlineStyle("font-size:small; margin:50px;");
        richLink.addActionListener(resolveActionListener("#{MainBean.removePanal}"));

        //richInputText.setContentStyle("font-weight:bold;color:green");
        addComponent(layOut, richLink);
        return richLink;
    }
    //---------------------------------------------------------------Preview Comp

    public RichOutputText createOutPutComp(String value,RichPanelGroupLayout layOut) {
        RichOutputText richOutPut = new RichOutputText();
        Random r = new Random();
        richOutPut.setId("rit1" + r.nextInt());
        richOutPut.setValue(value);
        richOutPut.setInlineStyle("font-size:20px; color:Red;");
        richOutPut.setVisible(false);
        richOutPut.setStyleClass("auto-hide");
        //richInputText.setContentStyle("font-weight:bold;color:green");
        addComponent(layOut, richOutPut);
        return richOutPut;
    }

    public RichPanelGroupLayout createaddCompPanalGroup(RichPanelGroupLayout parent, String layout) {
        RichPanelGroupLayout richOutPut = new RichPanelGroupLayout();
        Random r = new Random();
        richOutPut.setId("rit1" + r.nextInt());
        richOutPut.setLayout(layout);
        //richInputText.setContentStyle("font-weight:bold;color:green");
        addComponent(parent, richOutPut);
        return richOutPut;
    }

    public RichImage createImageComp(String path) {
        RichImage richImage = new RichImage();
        Random r = new Random();
        richImage.setId("rit1" + r.nextInt());
        richImage.setSource("/downloadimageclass?path=" + path);

        richImage.setInlineStyle("max-height:200px; max-width:300px;");
        addComponent(getPreviewPanalGroupComp(), richImage);
        return richImage;
    }

    private String uploadFile(UploadedFile file, String pth) {

        UploadedFile myfile = file;

        String path = null;
        if (myfile != null) {
            // All uploaded files will be stored in below path
            path = pth + myfile.getFilename();
            myfile.getFilename();
            System.out.println(">>>>filename" + file.getFilename());
            setImagePath(file.getFilename());
            fNAme = file.getFilename();
            InputStream inputStream = null;
            try {
                FileOutputStream out = new FileOutputStream(path);
                inputStream = myfile.getInputStream();
                byte[] buffer = new byte[8192];
                int bytesRead = 0;
                while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                out.flush();
                out.close();
            } catch (Exception ex) {
                // handle exception
                ex.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                    setImagePath(path);
                } catch (IOException e) {
                }
            }

        }
        //Returns the path where file is stored
        return path;
    }


    public void openNewWindow(String image, String content, String title, String thumbNail,String desc) {
        ExtendedRenderKitService erks =
            Service.getRenderKitService(FacesContext.getCurrentInstance(), ExtendedRenderKitService.class);
        //TODO
        //Change id to deployment server
        String contentEncod = "";
        String titleEncod = "";
        String descEncod="";
        try {
            if (!content.equals("") && content != null)
                contentEncod = URLEncoder.encode(content, "UTF-8");
            if (!title.equals("") && title != null)
                titleEncod = URLEncoder.encode(title, "UTF-8");
            if (!desc.equals("") && desc != null)
                descEncod = URLEncoder.encode(desc, "UTF-8");


        } catch (Exception e) {
        }
        System.out.println(">>>>>>>>desc"+descEncod+"DESCRIPTION "+desc+"title "+titleEncod+"content "+contentEncod);
        erks.addScript(FacesContext.getCurrentInstance(),
                       "window.open('/ViewController/htmlreviewservlet?image=" + image +
                       "&content=" + contentEncod + "&title=" + titleEncod + "&thumbnail=" + thumbNail +  "&desc=" + descEncod+"');");
    }


    public void setPreviewPanalGroupComp(RichPanelGroupLayout previewPanalGroupComp) {
        this.previewPanalGroupComp = previewPanalGroupComp;
    }

    public RichPanelGroupLayout getPreviewPanalGroupComp() {
        return previewPanalGroupComp;
    }


    public String createPreviewComponent() {
        // Add event code here...
        getPreviewPanalGroupComp().getChildren().clear();
        for (int i = 0; i < containersList.size(); i++) {
            HtmlInputTextarea inText = (HtmlInputTextarea) containersList.get(i).getChildren().get(0);
            RichInputFile imageComp = (RichInputFile) containersList.get(i).getChildren().get(1);
            String textVal = (String) inText.getValue();
            //createOutPutComp(textVal);

            if (imageComp.getValue() != null) {
                String imageSrc = binPath + ((UploadedFile) imageComp.getValue()).getFilename();
                createImageComp(imageSrc);
            }
        }
        return null;
    }

    private ValueChangeListener resolveValueChangeListener(String validatorName) {
        //ValueChangeListener method takes 1 argument of following type , we have to define that
        Class[] argtypes = new Class[1];
        argtypes[0] = ValueChangeEvent.class;
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        Application app = facesCtx.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesCtx.getELContext();
        MethodExpression methodExp = elFactory.createMethodExpression(elContext, validatorName, null, argtypes);
        return new MethodExpressionValueChangeListener(methodExp);
    }

    private ActionListener resolveActionListener(String validatorName) {
        Class[] argtypes = new Class[1];
        argtypes[0] = ActionEvent.class;
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        Application app = facesCtx.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesCtx.getELContext();
        MethodExpression methodExp = elFactory.createMethodExpression(elContext, validatorName, null, argtypes);
        return new MethodExpressionActionListener(methodExp);
    }

    private BlobDomain createBlobDomain(String value) throws IOException {

        BlobDomain blobDomain = new BlobDomain(value.getBytes());

        return blobDomain;
    }

    public void insertInNewsTable(String titleAr, String titleEN, String descAR, String descEN, BlobDomain contentAR,
                                  BlobDomain contentEN, BlobDomain thumbNail, String createdBy) {

        BindingContext ctx = BindingContext.getCurrent();
        DCBindingContainer container = (DCBindingContainer) ctx.getCurrentBindingsEntry();
        DCIteratorBinding it = container.findIteratorBinding("WcpNewsFeedIterator");
        ViewObject vo = it.getViewObject();
        Row row = vo.createRow();
        row.setAttribute(0, 1);
        row.setAttribute(1, titleAr);
        row.setAttribute(2, titleEN);
        row.setAttribute(3, descAR);
        row.setAttribute(4, descEN);
        row.setAttribute(5, contentAR);
        row.setAttribute(6, contentEN);
        row.setAttribute(7, thumbNail);
        row.setAttribute(15, createdBy);
        vo.insertRow(row);

    }


    public void setInArea(HtmlInputTextarea inArea) {
        this.inArea = inArea;
    }

    public HtmlInputTextarea getInArea() {
        return inArea;
    }


    public void test(ActionEvent actionEvent) {
        // Add event code here...
        //readBlob();
        //timer.cancel();
       
           
        
      
        

    }


    public String getContentString(Language lang) {
        String content = "";
        for (int i = 0; i < containersList.size(); i++) {
            if (lang == Language.ENGLISH) {
                RichInputText inText = (RichInputText) containersList.get(i).getChildren().get(0);
                String textVal = (String) inText.getValue();
                content += "<p>" + textVal + "</p>";
            } else {
                RichInputText inText = (RichInputText) containersList.get(i).getChildren().get(1);
                String textVal = (String) inText.getValue();
                content += "<p>" + textVal + "</p>";
            }
            //--------------------------------
            RichInputFile imageComp = (RichInputFile) containersList.get(i).getChildren().get(2);
            if (imageComp.getValue() != null) {
                String imageName = ((UploadedFile) imageComp.getValue()).getFilename();
                String imgDID = contentDIDMap.get(imageName);
                String url = getUcmURL(imgDID);
                content += "<img src=" + url + " alt='image' width=\"300\" height=\"400\" >";
            }

            i++;
        }


        return content;

    }


    public void insertData(ActionEvent actionEvent) {
        // Add event code here...

        if(checkImagesUploaded())
        try {
            insertInNewsTable(getArabicTitle(), getTitle(), getArabicDesc(), getEnglishDesc(),
                              createBlobDomain(getContentString(Language.ARABIC)),
                              createBlobDomain(getContentString(Language.ENGLISH)),
                              createBlobDomain(getHTMLThumbNail()), "Ragab");
        } catch (IOException e) {
        }
    
    }

    public String getHTMLThumbNail() {
        String htmlThumNail = "<img src=" + getUcmURL(htmlThumbNail) + " alt='image' width=\"300\" height=\"400\" >";
        return htmlThumNail;
    }


    public void readBlob() {
        BindingContext ctx = BindingContext.getCurrent();
        DCBindingContainer container = (DCBindingContainer) ctx.getCurrentBindingsEntry();
        DCIteratorBinding it = container.findIteratorBinding("WcpNewsFeedIterator");
        ViewObject vo = it.getViewObject();
        vo.executeQuery();
        if (vo.hasNext()) {
            Row r = vo.next();
            BlobDomain b = (BlobDomain) r.getAttribute(5);
            BlobDomain b1 = (BlobDomain) r.getAttribute(6);
            System.out.println(">>>>>>>>>>>>" + "Arabic:   " + b+ "  ,English:   " + b1);
        }

    }


    public void setArabicTitle(String arabicTitle) {
        this.arabicTitle = arabicTitle;
    }

    public String getArabicTitle() {
        return arabicTitle;
    }

    public void setEnglishDesc(String englishDesc) {
        this.englishDesc = englishDesc;
    }

    public String getEnglishDesc() {
        return englishDesc;
    }

    public void setArabicDesc(String arabicDesc) {
        this.arabicDesc = arabicDesc;
    }

    public String getArabicDesc() {
        return arabicDesc;
    }

    public void setIncasd(RichInputText incasd) {
        this.incasd = incasd;
    }

    public RichInputText getIncasd() {
        return incasd;
    }
     
   
  
   
    public void refresh(){
            FacesContext context = FacesContext.getCurrentInstance();
               String currentView = context.getViewRoot().getViewId();
               ViewHandler vh = context.getApplication().getViewHandler();
               UIViewRoot x = vh.createView(context, currentView);
               x.setViewId(currentView);
               context.setViewRoot(x);}
    
    
    public boolean checkImagesUploaded()
    {
        List<RichPanelGroupLayout> checkList=cloneList(containersList);
        boolean isReady=true;
        
        String thumbNailUrl=getUcmURL(htmlThumbNail);
        System.out.println(">>>>>>thambNail url "+thumbNailUrl+"        " +htmlThumbNail);
        if(thumbNailUrl==null ||thumbNailUrl.equals("")){
            isReady=false;
            thumbNailStateComp.setVisible(true);
            thumbNailStateComp.setValue("Uploading...");
            thumbNailStateComp.setInlineStyle("font-size:15px; color:Red;");
            try{
                context.addPartialTarget(getPanalGroupComp());
            }catch(Exception e){System.out.println(">>>>>ERROR"+e.getMessage());}
                System.out.println(">>>>>>>>>Ready");

        }else{
                thumbNailStateComp.setVisible(true);
                thumbNailStateComp.setValue("Ready");
                thumbNailStateComp.setInlineStyle("font-size:15px; color:Green;");
                try{
                    context.addPartialTarget(getPanalGroupComp());
                }catch(Exception e){System.out.println(">>>>>ERROR"+e.getMessage());}
                    System.out.println(">>>>>>>>>Ready");

            
            }
        for(int i=0;i<checkList.size();i++)
        {
            RichInputFile imageComp = (RichInputFile) checkList.get(i).getChildren().get(2);
            RichOutputText outputText = (RichOutputText) checkList.get(i).getChildren().get(3);
            if (imageComp.getValue() != null) {
                
                String imageName = ((UploadedFile) imageComp.getValue()).getFilename();
                System.out.println(">>>>>>>>"+imageName);
                String imgDID = contentDIDMap.get(imageName);
                String url = getUcmURL(imgDID);
                if(url!=null && !url.equals(""))
                {
                    outputText.setVisible(true);
                    outputText.setValue("Ready");
                    outputText.setInlineStyle("font-size:15px; color:Green;");
                    
                    try{
                        context.addPartialTarget(getPanalGroupComp());
                    }catch(Exception e){System.out.println(">>>>>ERROR"+e.getMessage());}
                        System.out.println(">>>>>>>>>Ready");

                    
                }else{
                        outputText.setInlineStyle("font-size:15px; color:Red;");
                        outputText.setVisible(true);
                        outputText.setValue("Uploading.....");
                    
                    isReady=false;
                try{
                    context.addPartialTarget(getPanalGroupComp());
                }catch(Exception e){System.out.println(">>>>>ERROR"+e.getMessage());}
                   
            }
        
        }
        // your code here
        
        }
       if(!isReady)
       {showUploadingWarn();}
       
        return isReady;
    }
    
    public String showUploadingWarn() {
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage fMessage= new FacesMessage(FacesMessage.SEVERITY_INFO,null,"Please wait till all files uploaded");
        
        ctx.addMessage(null, fMessage);
        
        ExtendedRenderKitService erks = Service.getRenderKitService(ctx, ExtendedRenderKitService.class);
        StringBuilder builder= new StringBuilder();
        builder.append("jQuery('.auto-hide').delay(3000).hide('fast');");
        erks.addScript(ctx, builder.toString());
        
        return null;
    }
    public  List<RichPanelGroupLayout> cloneList(List<RichPanelGroupLayout> list) {
         List<RichPanelGroupLayout> clone = new ArrayList<>(list.size());
        for (RichPanelGroupLayout item : list)
            clone.add(item);
        return clone;
    }


    public void showNotReadyMessage()
    {
            FacesMessage errorMessage =
                          new FacesMessage("Some files not ready", "Please Wait till all files uploaded");
                      errorMessage.setSeverity(FacesMessage.SEVERITY_FATAL);
                      FacesContext.getCurrentInstance().addMessage(null, errorMessage);
    }
    
    
    public void setThumbNailStateComp(RichOutputText thumbNailStateComp) {
        this.thumbNailStateComp = thumbNailStateComp;
    }

    public RichOutputText getThumbNailStateComp() {
        return thumbNailStateComp;
    }

    public enum Language {
        ENGLISH,
        ARABIC
    }
}
