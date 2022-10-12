package main.file;

public enum Tag {

    
	NUMBER("number"),
	DATE("date"),
	USER("user"),
	SUMM("summ"),
	CURRENCY("currency"),
	RATE("rate"),
	GOODS("goods"),
	AMOUNT("amount"),
	EMPLOYEE("employee"),
	PARTNER("partner"),
	COMMISSION("commission"),
	TYPE("type"),
	DOCUMENT("document"),
	ACCOUNTING_DOCUMENTS("accounting_documents");

	private final String tagName;

	private Tag(String tagName) {
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}

	public static Tag getTag(String tagName) {
		String tagFarmatted = tagName.strip();
		for (Tag tag : Tag.values()) {
			if (tagFarmatted.equals(tag.getTagName())) {
				return tag;
			}
		}
		return null;
	}

}
