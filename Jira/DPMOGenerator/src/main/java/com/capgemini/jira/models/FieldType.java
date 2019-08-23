package com.capgemini.jira.models;

public enum FieldType {
  SINGLE_SELECT("Select List (single choice)"),
  DATE_PICKER("Date Picker"),
  NUMBER_FIELD("Number Field"),
  USER_PICKER("User Picker"), /* not accurate */
  TEXT_FIELD("Text Field"), /* not accurate */
  UNKNOWN("Unknown");

  private final String value;

  FieldType(String value) {
    this.value = value;
  }

  private boolean equalsValue(String value) {
    return this.value.equals(value);
  }

  public String toString() {
    return this.value;
  }

  public static FieldType getFieldTypeByValue(String value) {
    for (FieldType typeValue : FieldType.values()) {
      if (typeValue.equalsValue(value)) {
        return typeValue;
      }
    }

    return UNKNOWN;
  }
}

