package br.org.serratec.backend.model;

public enum TaxasInss {
	TAXA1(0.09, 16.5), TAXA2(0.12, 82.61), TAXA3(0.14, 148.72), TAXA4(0.14, 148.72), TAXA5(0.075, 0);
	//kkkkkkkkkkkkkkkkkkkk
	private double taxa;
	private double deducao;

	private TaxasInss(double taxa, double deducao) {
		this.taxa = taxa;
		this.deducao = deducao;
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public double getDeducao() {
		return deducao;
	}

	public void setDeducao(double deducao) {
		this.deducao = deducao;
	}

}
