package com.nexbis.fwcms.domain.enums;

public enum EnumStatusResult {
	PASS("01"), FAIL("02"), PENDING("03");
	
	private String statusResultCode;
	private EnumStatusResult(String statusResultCode){
		this.statusResultCode = statusResultCode;
	}
	
	public static EnumStatusResult getByStatusResultCode(String statusResultCode) {
		for(EnumStatusResult enumStatusResult : values()) {
			if (statusResultCode != null) {
				if (statusResultCode.equals(enumStatusResult.getStatusResultCode())) {
					return enumStatusResult;
				}
			}
		}
		
		return null;
	}
	
	public String getStatusResultCode() {
        return statusResultCode;
    }
}
