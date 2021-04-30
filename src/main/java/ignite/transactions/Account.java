package ignite.transactions;

public class Account {
	private int ballance;
	private int fieldB;
	public Account(int fieldA, int fieldB) {
		super();
		this.ballance = fieldA;
		this.fieldB = fieldB;
	}

	public int getBallance() {
		return ballance;
	}

	public void setBallance(int ballance) {
		this.ballance = ballance;
	}

	@Override
	public String toString() {
		return "Account [ballance=" + ballance + ", fieldB=" + fieldB + "]";
	}

	public int getFieldB() {
		return fieldB;
	}
	public void setFieldB(int fieldB) {
		this.fieldB = fieldB;
	}
}
