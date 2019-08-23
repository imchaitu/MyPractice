package com.capgemini.jira.models;

public enum SystemField {
  SUMMARY("Summary"),
  PROJECT("Project"),
  ISSUETYPE("Issue Type"),
  ASSIGNEE("Assignee"),
  REPORTER("Reporter"),
  DUEDATE("Due Date"),
  DESCRIPTION("Description"),
  UNKNOWN("Unknown");

  private final String value;

  SystemField(String value) {
    this.value = value;
  }

  private boolean equalsValue(String value) {
    return this.value.equals(value);
  }

  public String toString() {
    return this.value;
  }

  public FieldType getFieldType() {
    FieldType fieldType = FieldType.UNKNOWN;
    switch (this) {
      case ASSIGNEE:
        fieldType = FieldType.USER_PICKER;
        break;
      case DESCRIPTION:
        fieldType = FieldType.TEXT_FIELD;
        break;
      case DUEDATE:
        fieldType = FieldType.DATE_PICKER;
        break;
      case ISSUETYPE:
        fieldType = FieldType.UNKNOWN;
        break;
      case PROJECT:
        fieldType = FieldType.UNKNOWN;
        break;
      case REPORTER:
        fieldType = FieldType.USER_PICKER;
        break;
      case SUMMARY:
        fieldType = FieldType.TEXT_FIELD;
        break;
    }

    return fieldType;
  }

  public static boolean containsValue(String value) {
    for (SystemField sysField : SystemField.values()) {
      if (sysField.equalsValue(value)) {
        return true;
      }
    }

    return false;
  }

  public static SystemField getSystemFieldByValue(String value) {
    for (SystemField sysField : SystemField.values()) {
      if (sysField.equalsValue(value)) {
        return sysField;
      }
    }

    return UNKNOWN;
  }
}
