package lps.bet.basico.onibus.validadorMgr;

public class ValidadorMgr implements IValidadorMgt{
	int validadorID=0, onibusID=0;
	
	public ValidadorMgr(){}
	
	public int getValidadorID(){
		return validadorID;
	}
	public int getOnibusID(){
		return onibusID;
	}
	public void setValidadorID(int id){
		validadorID = id;
	}
	public void setOnibusID(int id){
		onibusID = id;
	}
}
