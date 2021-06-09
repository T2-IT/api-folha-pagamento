package br.org.serratec.backend.model;

public enum TaxaIR {
	TAXA1(0.075, 142.80), TAXA3(0.15, 354.80), TAXA4(0.225, 636.13), TAXA5(0.275, 869.36);
	//kkkkkkkkkkkkkkkkkkkk
	private double aliquota;
	private double deducao;

	private TaxaIR(double aliquota, double deducao) {
		this.aliquota = aliquota;
		this.deducao = deducao;
	}

	public double getAliquota() {
		return aliquota;
	}

	public void setAliquota(double aliquota) {
		this.aliquota = aliquota;
	}

	public double getDeducao() {
		return deducao;
	}

	public void setDeducao(double deducao) {
		this.deducao = deducao;
	}

}
