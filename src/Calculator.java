
public class Calculator {
	public int add(int a, int b){
		return a+b;
	}
	public int minus(int a, int b){
		return a-b;
	}
	public int multiple(int a, int b){
		return a*b;
	}
	public int divide(int a, int b) throws Exception{
		if(0==b){
			throw new Exception("��������Ϊ�㣡");
		}
		return a/b;
	}
}