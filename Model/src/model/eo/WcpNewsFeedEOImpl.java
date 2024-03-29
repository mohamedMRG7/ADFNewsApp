package model.eo;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.jbo.Key;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.domain.Number;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jun 25 16:57:25 EET 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class WcpNewsFeedEOImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        NfSeq,
        NfTitleAr,
        NfTitleEn,
        NfDescAr,
        NfDescEn,
        NfContentAr,
        NfContentEn,
        NfThumbnail,
        NfActiveStDate,
        NfActiveEndDate,
        NfIsActive,
        NfIsDeleted,
        NfDeletedDate,
        NfLastUpdatedDate,
        NfCreatedDate,
        NfCreatedBy,
        NfUpdatedBy;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int NFSEQ = AttributesEnum.NfSeq.index();
    public static final int NFTITLEAR = AttributesEnum.NfTitleAr.index();
    public static final int NFTITLEEN = AttributesEnum.NfTitleEn.index();
    public static final int NFDESCAR = AttributesEnum.NfDescAr.index();
    public static final int NFDESCEN = AttributesEnum.NfDescEn.index();
    public static final int NFCONTENTAR = AttributesEnum.NfContentAr.index();
    public static final int NFCONTENTEN = AttributesEnum.NfContentEn.index();
    public static final int NFTHUMBNAIL = AttributesEnum.NfThumbnail.index();
    public static final int NFACTIVESTDATE = AttributesEnum.NfActiveStDate.index();
    public static final int NFACTIVEENDDATE = AttributesEnum.NfActiveEndDate.index();
    public static final int NFISACTIVE = AttributesEnum.NfIsActive.index();
    public static final int NFISDELETED = AttributesEnum.NfIsDeleted.index();
    public static final int NFDELETEDDATE = AttributesEnum.NfDeletedDate.index();
    public static final int NFLASTUPDATEDDATE = AttributesEnum.NfLastUpdatedDate.index();
    public static final int NFCREATEDDATE = AttributesEnum.NfCreatedDate.index();
    public static final int NFCREATEDBY = AttributesEnum.NfCreatedBy.index();
    public static final int NFUPDATEDBY = AttributesEnum.NfUpdatedBy.index();

    /**
     * This is the default constructor (do not remove).
     */
    public WcpNewsFeedEOImpl() {
    }

    /**
     * Gets the attribute value for NfSeq, using the alias name NfSeq.
     * @return the value of NfSeq
     */
    public BigDecimal getNfSeq() {
        return (BigDecimal) getAttributeInternal(NFSEQ);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfSeq.
     * @param value value to set the NfSeq
     */
    public void setNfSeq(BigDecimal value) {
        setAttributeInternal(NFSEQ, value);
    }

    /**
     * Gets the attribute value for NfTitleAr, using the alias name NfTitleAr.
     * @return the value of NfTitleAr
     */
    public String getNfTitleAr() {
        return (String) getAttributeInternal(NFTITLEAR);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfTitleAr.
     * @param value value to set the NfTitleAr
     */
    public void setNfTitleAr(String value) {
        setAttributeInternal(NFTITLEAR, value);
    }

    /**
     * Gets the attribute value for NfTitleEn, using the alias name NfTitleEn.
     * @return the value of NfTitleEn
     */
    public String getNfTitleEn() {
        return (String) getAttributeInternal(NFTITLEEN);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfTitleEn.
     * @param value value to set the NfTitleEn
     */
    public void setNfTitleEn(String value) {
        setAttributeInternal(NFTITLEEN, value);
    }

    /**
     * Gets the attribute value for NfDescAr, using the alias name NfDescAr.
     * @return the value of NfDescAr
     */
    public String getNfDescAr() {
        return (String) getAttributeInternal(NFDESCAR);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfDescAr.
     * @param value value to set the NfDescAr
     */
    public void setNfDescAr(String value) {
        setAttributeInternal(NFDESCAR, value);
    }

    /**
     * Gets the attribute value for NfDescEn, using the alias name NfDescEn.
     * @return the value of NfDescEn
     */
    public String getNfDescEn() {
        return (String) getAttributeInternal(NFDESCEN);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfDescEn.
     * @param value value to set the NfDescEn
     */
    public void setNfDescEn(String value) {
        setAttributeInternal(NFDESCEN, value);
    }

    /**
     * Gets the attribute value for NfContentAr, using the alias name NfContentAr.
     * @return the value of NfContentAr
     */
    public BlobDomain getNfContentAr() {
        return (BlobDomain) getAttributeInternal(NFCONTENTAR);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfContentAr.
     * @param value value to set the NfContentAr
     */
    public void setNfContentAr(BlobDomain value) {
        setAttributeInternal(NFCONTENTAR, value);
    }

    /**
     * Gets the attribute value for NfContentEn, using the alias name NfContentEn.
     * @return the value of NfContentEn
     */
    public BlobDomain getNfContentEn() {
        return (BlobDomain) getAttributeInternal(NFCONTENTEN);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfContentEn.
     * @param value value to set the NfContentEn
     */
    public void setNfContentEn(BlobDomain value) {
        setAttributeInternal(NFCONTENTEN, value);
    }

    /**
     * Gets the attribute value for NfThumbnail, using the alias name NfThumbnail.
     * @return the value of NfThumbnail
     */
    public BlobDomain getNfThumbnail() {
        return (BlobDomain) getAttributeInternal(NFTHUMBNAIL);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfThumbnail.
     * @param value value to set the NfThumbnail
     */
    public void setNfThumbnail(BlobDomain value) {
        setAttributeInternal(NFTHUMBNAIL, value);
    }

    /**
     * Gets the attribute value for NfActiveStDate, using the alias name NfActiveStDate.
     * @return the value of NfActiveStDate
     */
    public Timestamp getNfActiveStDate() {
        return (Timestamp) getAttributeInternal(NFACTIVESTDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfActiveStDate.
     * @param value value to set the NfActiveStDate
     */
    public void setNfActiveStDate(Timestamp value) {
        setAttributeInternal(NFACTIVESTDATE, value);
    }

    /**
     * Gets the attribute value for NfActiveEndDate, using the alias name NfActiveEndDate.
     * @return the value of NfActiveEndDate
     */
    public Timestamp getNfActiveEndDate() {
        return (Timestamp) getAttributeInternal(NFACTIVEENDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfActiveEndDate.
     * @param value value to set the NfActiveEndDate
     */
    public void setNfActiveEndDate(Timestamp value) {
        setAttributeInternal(NFACTIVEENDDATE, value);
    }

    /**
     * Gets the attribute value for NfIsActive, using the alias name NfIsActive.
     * @return the value of NfIsActive
     */
    public String getNfIsActive() {
        return (String) getAttributeInternal(NFISACTIVE);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfIsActive.
     * @param value value to set the NfIsActive
     */
    public void setNfIsActive(String value) {
        setAttributeInternal(NFISACTIVE, value);
    }

    /**
     * Gets the attribute value for NfIsDeleted, using the alias name NfIsDeleted.
     * @return the value of NfIsDeleted
     */
    public String getNfIsDeleted() {
        return (String) getAttributeInternal(NFISDELETED);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfIsDeleted.
     * @param value value to set the NfIsDeleted
     */
    public void setNfIsDeleted(String value) {
        setAttributeInternal(NFISDELETED, value);
    }

    /**
     * Gets the attribute value for NfDeletedDate, using the alias name NfDeletedDate.
     * @return the value of NfDeletedDate
     */
    public Timestamp getNfDeletedDate() {
        return (Timestamp) getAttributeInternal(NFDELETEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfDeletedDate.
     * @param value value to set the NfDeletedDate
     */
    public void setNfDeletedDate(Timestamp value) {
        setAttributeInternal(NFDELETEDDATE, value);
    }

    /**
     * Gets the attribute value for NfLastUpdatedDate, using the alias name NfLastUpdatedDate.
     * @return the value of NfLastUpdatedDate
     */
    public Timestamp getNfLastUpdatedDate() {
        return (Timestamp) getAttributeInternal(NFLASTUPDATEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfLastUpdatedDate.
     * @param value value to set the NfLastUpdatedDate
     */
    public void setNfLastUpdatedDate(Timestamp value) {
        setAttributeInternal(NFLASTUPDATEDDATE, value);
    }

    /**
     * Gets the attribute value for NfCreatedDate, using the alias name NfCreatedDate.
     * @return the value of NfCreatedDate
     */
    public Timestamp getNfCreatedDate() {
        return (Timestamp) getAttributeInternal(NFCREATEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfCreatedDate.
     * @param value value to set the NfCreatedDate
     */
    public void setNfCreatedDate(Timestamp value) {
        setAttributeInternal(NFCREATEDDATE, value);
    }

    /**
     * Gets the attribute value for NfCreatedBy, using the alias name NfCreatedBy.
     * @return the value of NfCreatedBy
     */
    public String getNfCreatedBy() {
        return (String) getAttributeInternal(NFCREATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfCreatedBy.
     * @param value value to set the NfCreatedBy
     */
    public void setNfCreatedBy(String value) {
        setAttributeInternal(NFCREATEDBY, value);
    }

    /**
     * Gets the attribute value for NfUpdatedBy, using the alias name NfUpdatedBy.
     * @return the value of NfUpdatedBy
     */
    public String getNfUpdatedBy() {
        return (String) getAttributeInternal(NFUPDATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for NfUpdatedBy.
     * @param value value to set the NfUpdatedBy
     */
    public void setNfUpdatedBy(String value) {
        setAttributeInternal(NFUPDATEDBY, value);
    }

    /**
     * @param nfSeq key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(BigDecimal nfSeq) {
        return new Key(new Object[] { nfSeq });
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("model.eo.WcpNewsFeedEO");
    }


    /**
     * Add locking logic here.
     */
    public void lock() {
        super.lock();
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        if(operation==DML_INSERT)
        {
            SequenceImpl seq=new  SequenceImpl("NEWS_SEQ",getDBTransaction());
            BigDecimal number = seq.getSequenceNumber().getBigDecimalValue();
            
            setNfSeq(number);
            super.doDML(operation, e);
        }
    }
}

