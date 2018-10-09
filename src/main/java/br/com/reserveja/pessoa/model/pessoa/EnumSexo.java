package br.com.reserveja.pessoa.model.pessoa;

public enum EnumSexo {
	MASCULINO("M"), FEMININO("F");
	
	private String value;
	
	private EnumSexo(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
