package stm.stm.enumy;

public enum Status {
    NEW, IN_PROGRESS, DONE
}
    private  final String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
