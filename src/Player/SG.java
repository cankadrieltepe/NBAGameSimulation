package Player;

import java.util.Random;

public class SG extends player{

	public SG(String Player_name, String position, double PTS, double TRB, double AST,double STL,double BLK,double score) {
		super(Player_name, position, PTS, TRB, AST, STL,BLK,score);
		// TODO Auto-generated constructor stub
	}
	protected double PTS_weight=0.1;
	protected double TRB_weight=0.2;
	protected double AST_weight=0.15;
	protected double BLK_weight=0.3;
	protected double STL_weight=0.25;
	public double getPTS_weight() {
		return PTS_weight;
	}
	public double getTRB_weight() {
		return TRB_weight;
	}
	public double getAST_weight() {
		return AST_weight;
	}
	public double getBLK_weight() {
		return BLK_weight;
	}
	public double getSTL_weight() {
		return STL_weight;
	}
	public int Scoring(player Player) {
		//because the instructions says pick an integer I round up the doubles and turn them into integers.
		// nextInt(value+N-(value-N)+value-N  is my implementation for finding the integer
		//I pick N=5 for PTS, N=3 for TRB, N=2 for AST, N=1 for STL,N=1 for BLK
		Random random = new Random();
		int PTS_NO=0;
		while(PTS_NO==0) {
		PTS_NO=random.nextInt(10)+(int)Math.round(Player.getPTS())-5;
		}int TRB_NO=0;
		while(TRB_NO==0) {
		TRB_NO=random.nextInt(6)+(int)Math.round(Player.getTRB())-3;			
		}int AST_NO=0;
		while(AST_NO==0) {
		AST_NO=random.nextInt(4)+(int)Math.round(Player.getAST())-2;
		}int STL_NO=0;
		while(STL_NO==0) {
		STL_NO=random.nextInt(2)+(int)Math.round(Player.getSTL())-1;
		}int BLK_NO=0;
		while(BLK_NO==0) {
		BLK_NO=random.nextInt(2)+(int)Math.round(Player.getBLK())-1;
		}
		int score=(int)Math.round(PTS_weight*PTS_NO+TRB_weight*TRB_NO+AST_weight*AST_NO+BLK_weight*BLK_NO+STL_weight*STL_NO);
		return score;
	}
}
