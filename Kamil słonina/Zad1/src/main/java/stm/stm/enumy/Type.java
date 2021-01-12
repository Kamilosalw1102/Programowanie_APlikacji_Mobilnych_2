package stm.stm.enumy;

public enum Type implements java.lang.reflect.Type {
    TASK, BUG, FEATURE
}

    Type(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}

