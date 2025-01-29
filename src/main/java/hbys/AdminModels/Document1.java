/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hbys.AdminModels;

/**
 *
 * @author Rafet Bartuğ
 */
public class Document1 {

    private int documentID;
    private String documentName;
    private String documentType;
    private String status;

    // Constructor
    public Document1(int documentID, String documentName, String documentType, String status) {
        this.documentID = documentID;
        this.documentName = documentName;
        this.documentType = documentType;
        this.status = status;
    }

    // Getters and Setters
    public int getDocumentID() {
        return documentID;
    }

    public void setDocumentID(int documentID) {
        this.documentID = documentID;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return documentName + " (" + status + ")";
    }
}
