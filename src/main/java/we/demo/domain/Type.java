package we.demo.domain;

public enum Type {
    EXCLUDE_HTML_TAG("HTML 태그 제외"),
    TEXT_ALL("Text 전체");

    private final String description;

    Type(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
