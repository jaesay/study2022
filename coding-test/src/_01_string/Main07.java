package _01_string;

import java.util.Scanner;

public class Main07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] chars = sc.nextLine().toLowerCase().toCharArray();

        boolean yes = true;
        int lt = 0;
        int rt = chars.length - 1;
        while (lt < rt) {
            if (chars[lt] == chars[rt]) {
                lt++;
                rt--;
            } else {
                yes = false;
                break;
            }
        }
        if (yes) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}

/*
class Main {
	public String solution(String str){
		String answer="YES";
		str=str.toUpperCase();
		int len=str.length();
		for(int i=0; i<len/2; i++){
			if(str.charAt(i)!=str.charAt(len-i-1)) answer="NO";
		}
		return answer;
	}

	public static void main(String[] args){
		Main T = new Main();
		Scanner kb = new Scanner(System.in);
		String str=kb.next();
		System.out.print(T.solution(str));
	}
}



import java.util.*;
class Main {
	public String solution(String str){
		String answer="NO";
		String tmp=new StringBuilder(str).reverse().toString();
		if(str.equalsIgnoreCase(tmp)) answer="YES";
		return answer;
	}

	public static void main(String[] args){
		Main T = new Main();
		Scanner kb = new Scanner(System.in);
		String str=kb.next();
		System.out.print(T.solution(str));
	}
}


 */