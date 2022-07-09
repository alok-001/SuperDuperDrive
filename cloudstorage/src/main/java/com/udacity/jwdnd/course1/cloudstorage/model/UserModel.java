package com.udacity.jwdnd.course1.cloudstorage.model;

import java.io.Serializable;

public class UserModel implements Serializable {

    private Integer ModeluserId;
    private String ModelfirstName;
    private String ModellastName;
    private String Modelusername;
    private String Modelpassword;
    private String Modelsalt;

    public UserModel(Integer ModeluserId,
    String ModelfirstName,
    String ModellastName,
    String Modelusername,
    String Modelpassword,
    String Modelsalt){
        this.ModelfirstName = ModelfirstName;
        this.ModellastName  = ModellastName;
        this.Modelpassword = Modelpassword;
        this.Modelsalt = Modelsalt;
        this.ModeluserId = ModeluserId;
        this.Modelusername = Modelusername;
    }

    public Integer getModeluserId() {
        return ModeluserId;
    }

    public String getModelfirstName() {
        return ModelfirstName;
    }

    public String getModellastName() {
        return ModellastName;
    }

    public String getModelpassword() {
        return Modelpassword;
    }

    public String getModelsalt() {
        return Modelsalt;
    }

    public String getModelusername() {
        return Modelusername;
    }

    public void setModelfirstName(String modelfirstName) {
        ModelfirstName = modelfirstName;
    }

    public void setModellastName(String modellastName) {
        ModellastName = modellastName;
    }

    public void setModelpassword(String modelpassword) {
        Modelpassword = modelpassword;
    }

    public void setModelsalt(String modelsalt) {
        Modelsalt = modelsalt;
    }

    public void setModeluserId(Integer modeluserId) {
        ModeluserId = modeluserId;
    }

    public void setModelusername(String modelusername) {
        Modelusername = modelusername;
    }

}
