package oop.codblock;

public class ScoreTest {
	public static void main(String[] args) {
		char answers[][]= {
				{'A','B','A','C','C','D','E','E','A','D'},
				{'D','B','A','B','C','A','E','E','A','D'},
				{'E','D','D','A','C','B','E','E','A','D'},
				{'C','B','A','E','D','C','E','E','A','D'},
				{'A','B','D','C','C','D','E','E','A','D'},
				{'B','B','E','C','C','D','E','E','A','D'},
				{'B','B','A','C','C','D','E','E','A','D'},
				{'E','B','E','C','C','D','E','E','A','D'}};
		char[] keys= {'D','B','D','C','C','D','A','E','A','D'};
		int correctCount[]=new int[answers.length];
		int order[]= {0,1,2,3,4,5,6,7};
		for(int i=0;i<answers.length-1;i++) {
			for(int j=0;j<answers[i].length;j++) {
				if(answers[i][j]==keys[j])
				correctCount[i]++;
			}
		}
		for(int i=0;i<answers.length;i++) {
			System.out.println(correctCount[i]);
		}
		for(int i=0;i<answers.length;i++) {
			for(int j=0;j<answers.length-1;j++) {
				//错误原因 :此处条件j<answers.length-1错写为j<answers.length-i
				if(correctCount[j]>=correctCount[j+1]) {
					correctCount[j]=correctCount[j+1];
					int temp;
					temp=order[j];
					order[j]=order[j+1];
					order[j+1]=temp;
				}
				System.out.println("haha");
			}
		}
		for(int i=0;i<8;i++) {
			System.out.println(order[i]);
		}
	}
}
