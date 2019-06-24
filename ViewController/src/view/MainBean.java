package view;


import java.awt.Image;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Random;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import javax.el.MethodExpression;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionValueChangeListener;
import javax.faces.event.ValueChangeEvent;

import javax.faces.event.ValueChangeListener;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;


public class MainBean implements Serializable {
    private RichPanelGroupLayout panalGroupComp;
    private RichInputFile input;
    List<RichInputText> inputTextList=new ArrayList<>();
    List<RichInputFile> inputFileList=new ArrayList<>();
    private RichPanelGroupLayout previewPanalGroupComp;
    private RichOutputText f;
    private RichImage ds;
    private RichDialog dialog;
    private HtmlGraphicImage imgageBind;
    private String imagePath="";
    private String fNAme;
    private String binPath="C:\\oracle\\Middleware1\\jdeveloper\\jdev\\bin\\";
    private String jdevPath="C:\\Users\\Abdelrahman\\AppData\\Roaming\\JDeveloper\\system12.1.3.0.41.140521.1008\\o.j2ee\\drs\\NewsApplication\\ViewControllerWebApp.war\\images\\";
    String title;
    String thumbNail;
    String htmlThumbNail;
    int i=0;

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

    public MainBean() {
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
                   //Upload File to path- Return actual server path
                  thumbNail=uploadFile(fileVal,binPath);
                  uploadFile(fileVal,jdevPath);
                   htmlThumbNail=thumbNail.replace(binPath, "");

               }
    }

    public void uploadContentImage(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        if (valueChangeEvent.getNewValue() != null) {
                   //Get File Object from VC Event
                   UploadedFile fileVal = (UploadedFile) valueChangeEvent.getNewValue();
                   //Upload File to path- Return actual server path
                   uploadFile(fileVal,binPath);
                   uploadFile(fileVal,jdevPath);

                   

               }
    }

    public void addComponnent(ActionEvent actionEvent) {
        // Add event code here...
        
       inputTextList.add(createInputTextComponent());
       inputFileList.add(createFileUploadeComp());

        
    }
    public void preview(ActionEvent actionEvent) {
        // Add event code here...
        fNAme="";
        String contesnt="";
        int i=0;
        for (RichInputFile file:inputFileList)
        {
               fNAme+=((UploadedFile)file.getValue()).getFilename()+",";
               contesnt+=inputTextList.get(i).getValue().toString()+",";
        i++;
        }
        
        openNewWindow(fNAme,contesnt,title,htmlThumbNail);
    }

    
    
    
    
    //
    public void addComponent(UIComponent parentUIComponent, UIComponent childUIComponent) {
           parentUIComponent.getChildren().add(childUIComponent);
           AdfFacesContext.getCurrentInstance().addPartialTarget(parentUIComponent);
       }
    
    
    public RichInputText createInputTextComponent() {
           RichInputText richInputText = new RichInputText();
           Random r=new Random();
           richInputText.setId("rit1"+r.nextInt());
           richInputText.setLabel("Content");
           //richInputText.setContentStyle("font-weight:bold;color:green");
           addComponent(getPanalGroupComp(), richInputText);
           return richInputText;
       }
    
    public RichInputFile createFileUploadeComp() {
           RichInputFile richInputFile = new RichInputFile();
           Random r=new Random();
           richInputFile.setId("rit1"+r.nextInt());
           richInputFile.setLabel("Image");
           richInputFile.setAutoSubmit(true);
           richInputFile.addValueChangeListener(resolveValueChangeListener("#{MainBean.uploadContentImage}"));
           //richInputText.setContentStyle("font-weight:bold;color:green");
           addComponent(getPanalGroupComp(), richInputFile);
           return richInputFile;
       }
    
    
    public RichOutputText createOutPutComp(String value) {
           RichOutputText richOutPut=new RichOutputText() ;
           Random r=new Random();
           richOutPut.setId("rit1"+r.nextInt());
           richOutPut.setValue(value);
           //richInputText.setContentStyle("font-weight:bold;color:green");
           addComponent(getPreviewPanalGroupComp(), richOutPut);
           return richOutPut;
       }
    
    public RichImage createImageComp(String path) {
           RichImage richImage=new RichImage() ;
           Random r=new Random();
           richImage.setId("rit1"+r.nextInt());
           richImage.setSource("/downloadimageclass?path="+path);
           
           richImage.setInlineStyle("max-height:200px; max-width:300px;");
           addComponent(getPreviewPanalGroupComp(), richImage);
           return richImage;
       }
    
    private String uploadFile(UploadedFile file,String pth) {

           UploadedFile myfile = file;
           String path = null;
           if (myfile != null) {
               // All uploaded files will be stored in below path
               path = pth+ myfile.getFilename();
               myfile.getFilename();
               System.out.println(">>>>filename"+file.getFilename());
               setImagePath(file.getFilename());
               fNAme=file.getFilename();
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


    public void openNewWindow(String image,String content,String title,String thumbNail)
    {
       ExtendedRenderKitService erks =          
          Service.getRenderKitService(FacesContext.getCurrentInstance(),         
          ExtendedRenderKitService.class);

          erks.addScript(FacesContext.getCurrentInstance(), 
          "window.open('http://127.0.0.1:7101/ViewController/htmlreviewservlet?image="+image+"&content="+content+"&title="+title+"&thumbnail="+thumbNail+"');");
      }

  
    public void setPreviewPanalGroupComp(RichPanelGroupLayout previewPanalGroupComp) {
        this.previewPanalGroupComp = previewPanalGroupComp;
    }

    public RichPanelGroupLayout getPreviewPanalGroupComp() {
        return previewPanalGroupComp;
    }

    public String createPreviewComponent() {
        // Add event code here...
        for(;i<inputTextList.size();i++)
        {
            String textVal = (String) inputTextList.get(i).getValue();
            String imageSrc =  binPath+((UploadedFile)inputFileList.get(i).getValue()).getFilename();
           
           createOutPutComp(textVal);
            createImageComp(imageSrc);
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

 
    public void dialogLisner(DialogEvent dialogEvent) {
        // Add event code here...
    }

    
}
