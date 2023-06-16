package workers;

public class MyEntity {
    private int code;
    private String history;

    public MyEntity(int code, String history) {
        this.code = code;
        this.history = history;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
