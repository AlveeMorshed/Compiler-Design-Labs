import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class LexicalAnalyzer {
	String[]keyword = {"int", "float", "char", "double", "String", "if", "else"};
	String[]operator = {"+", "-", "*", "/", "%", "=", "++", "--", "+=", "-=", "*=","/=","%=", "^"};
	ArrayList<String> foundOps = new ArrayList<String>();
	String[]logicalOperator = {"<", "<=", ">", ">=","==", "!=", "&&", "||", "!"};
	ArrayList<String> foundLogOps = new ArrayList<String>();
	ArrayList<String> numericValues = new ArrayList<String>();
	ArrayList<String> other = new ArrayList<String>();
	String[]modLineArr;
	String modifiedLine, keywords = "", variableNames = "", operators = "";
	String logicalOperators = "", nums = "", others = "";
	public void analyze() {
		try {
			File input = new File("input.txt");
			Scanner sc = new Scanner(input);
			while(sc.hasNextLine()) {
				String currentLine = sc.nextLine();
				String[]lineArray = currentLine.split(" ", 2);
				if(Arrays.asList(keyword).contains(lineArray[0])) {
					keywords = addWord(keywords, lineArray[0]);
				}
				
				if(Arrays.asList(keyword).contains(lineArray[0]) && lineArray[lineArray.length-1].endsWith(";")){
					String[]identifier = (lineArray[lineArray.length-1].split(","));
					for(int i=0; i<identifier.length; ++i) {
						identifier[i] = identifier[i].replaceAll(",", "");
						identifier[i] = identifier[i].replaceAll(";", "");
						identifier[i] = identifier[i].replaceAll(" ", "");
						variableNames = addWord(variableNames, identifier[i]);
					
					}
					
				}
				for(int i=0; i<operator.length; ++i) {
					if(currentLine.contains(operator[i]) && !foundOps.contains(operator[i])){
						foundOps.add(operator[i]);
					}
				}
				for(int i=0; i<logicalOperator.length; ++i) {
					if(currentLine.contains(logicalOperator[i]) && !foundLogOps.contains(logicalOperator[i])){
						foundLogOps.add(logicalOperator[i]);
					}
				}
				modifiedLine = currentLine;
				for(int i=0; i<foundOps.size(); ++i) {
					if(modifiedLine.contains(foundOps.get(i))) {
						if(foundOps.get(i).equals("+") || foundOps.get(i).equals("*") || foundOps.get(i).equals("^")) {
							modifiedLine = modifiedLine.replaceAll(" \\"+foundOps.get(i)+" ", "~");
						}
						else {
							modifiedLine = modifiedLine.replaceAll(" "+foundOps.get(i)+" ", "~");
						}
						modifiedLine = modifiedLine.replaceAll("\\(", "");
						modifiedLine = modifiedLine.replaceAll("\\)", "");
						modifiedLine = modifiedLine.replaceAll(";", "");
					}
					
				}
				for(int i=0; i<foundLogOps.size(); ++i) {
					if(modifiedLine.contains(foundLogOps.get(i))) {
						if(foundLogOps.get(i).equals("+") || foundLogOps.get(i).equals("*") || foundLogOps.get(i).equals("^")) {
							modifiedLine = modifiedLine.replaceAll(" \\"+foundLogOps.get(i)+" ", "~");
						}
						else {
							modifiedLine = modifiedLine.replaceAll(" "+foundLogOps.get(i)+" ", "~");
						}
						modifiedLine = modifiedLine.replaceAll("\\(", "");
						modifiedLine = modifiedLine.replaceAll("\\)", "");
						modifiedLine = modifiedLine.replaceAll(";", "");
					}
					
				}
				modLineArr = modifiedLine.split("~");
				for(int i=0; i<modLineArr.length; ++i) {
					if(Character.isDigit(modLineArr[i].charAt(0))) {
						numericValues.add(modLineArr[i]);
					}
				}
				lineArray = currentLine.split(" ");
				for(int i=0; i<lineArray.length; ++i) {
					for(int j=0; j<lineArray[i].length(); ++j) {
						if(!other.contains(lineArray[i].charAt(j)+"") && lineArray[i].charAt(j)!= '.' && !Character.isWhitespace(lineArray[i].charAt(j))
								&& !Character.isLetter(lineArray[i].charAt(j))
								&& !Character.isDigit(lineArray[i].charAt(j))
								&& !foundOps.contains(lineArray[i].charAt(j)+"")
								&& !foundLogOps.contains(lineArray[i].charAt(j)+"")) {
							
							other.add(lineArray[i].charAt(j)+"");
						}
					}
				}
			}
			sc.close();
			Collections.sort(foundOps);
			Collections.sort(foundLogOps);
			for(String op : foundOps) {
				operators = addWord(operators, op);
			}
			Collections.sort(foundLogOps);
			for(String op : foundLogOps) {
				logicalOperators = addWord(logicalOperators, op);
			}
			for(String n : numericValues) {
				nums = addWord(nums, n);
			}
			for(String symbols : other) {
				others = others + symbols;
			}
			System.out.println("Keywords: "+keywords);
			System.out.println("Identifiers: "+variableNames);
			System.out.println("Math Operators: "+operators);
			System.out.println("Logical Operators: "+logicalOperators);
			System.out.println("Numerical Values: "+nums);
			System.out.println("Others: "+others);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String addWord(String sentence, String word) {
		if(sentence.equals("")) {
			sentence = sentence + word;
		}else {
			sentence = sentence + ", " + word;
		}
		return sentence;
	}
}