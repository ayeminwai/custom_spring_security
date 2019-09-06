package com.wirecard.sg.sample.enumator;

public class CommonEnum {
	public enum HistoryTable {
		//don't edit previous data, add new by serial number
		USER_DETAIL_HISTORY("user_detail_history", 1), USER_HISTORY("user_history", 2);

		private final String key;
		private final Integer value;

		HistoryTable(String key, Integer value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public Integer getValue() {
			return value;
		}
		
	}
}
