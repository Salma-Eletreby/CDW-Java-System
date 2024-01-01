package model;

public class Bin {
	
	private BinType binType;
	private int totalStock;
	private int totalDelivered;
	
	public Bin(BinType binType, int totalStock) {
		this.binType = binType;
		this.totalStock = totalStock;
	}

	public BinType getBinType() {
		return binType;
	}

	public int getTotalStock() {
		return totalStock;
	}

	public int getTotalDelivered() {
		return totalDelivered;
	}
	
	protected void deliverBin() {
		totalStock--;
		totalDelivered++;
	}
	
	protected void collectBin() {
		totalStock++;
		totalDelivered--;
	}

}
