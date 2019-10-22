package by.trjava.pashkovich.facultative.entity.characteristic;

public enum ApplyStatus {
    FIELD_APPLIED(1), ENROLLED_IN_COURSES(2), LEARNED(3);
    private int statusId;

    ApplyStatus(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public static ApplyStatus getStatus(int statusId) {
        ApplyStatus status = null;
        switch (statusId) {
            case 1:
                status = FIELD_APPLIED;
                break;
            case 2:
                status = ENROLLED_IN_COURSES;
                break;
            case 3:
                status = LEARNED;
                break;
        }
        return status;
    }
}
