package characterProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharacterMain {
	public static Scanner sc = new Scanner(System.in);
	public static final int INPUT = 1, PRINT = 2, ANALYZE = 3, EARCH = 4, UPDATE = 5, SORT = 6, DELETE = 7, EXIT = 8;;

	public static void main(String[] args) {
		ArrayList<Character> list = new ArrayList<Character>();
		DBConnection dbCon = new DBConnection();
		boolean run = true;
		int no = 0;
		// while
		while (run) {
			System.out.println(
					"+========================================[ ĳ���� �������α׷� ]========================================+");
			System.out.println("|		 1 �������� | 2��� | 3�м� | 4�˻� | 5���� | 6����(���ռ�) | 7���� | 8Ż��	  	          |");
			System.out.println(
					"+==================================================================================================+");
			System.out.print("> ");
			no = Integer.parseInt(sc.nextLine());
			// switch
			switch (no) {
			case INPUT:
				Character character = inputDataCharacter();
				// DB�Է�
				int rValue = dbCon.insert(character);
				if (rValue == 1) {
					System.out.println("���Լ���");
				} else {
					System.err.println("���Խ���");
				}
				break;
			case PRINT:
				ArrayList<Character> list2 = dbCon.select();
				if (list2 == null) {
					System.out.println("���� ����!");
				} else {
					printCharacter(list2);
				}
				break;
			case ANALYZE:
				ArrayList<Character> list3 = dbCon.analizeSelect();
				if (list3 == null) {
					System.out.println("���� ����!");
				} else {
					analyzeData(list3);
				}
				break;
			case EARCH:
				String name = searchCharacter();
				ArrayList<Character> list4 = dbCon.nameSearch(name);
				if (list4.size() >= 1) {
					printCharacter(list4);
				} else {
					System.err.println("ĳ���� �̸� �˻� ����");
				}
				break;
			case UPDATE:
				int updateRetrunValue = 0;
				int id = inputId(); // id�� ���ؼ� ��
				Character characters = dbCon.selectId(id);
				if (characters == null) {
					System.out.println("��������");
				} else {
					Character updatecharacter = updataeCharacter(characters);
					updateRetrunValue = dbCon.update(updatecharacter);
				}
				if (updateRetrunValue == 1) {
					System.out.println("UPDATE ����");
				} else {
					System.err.println("UPDATE ����");
				}
				break;
			case SORT:
				ArrayList<Character> list5 = dbCon.selectSort();
				if (list5 == null) {
					System.err.println("���� ����");
				} else {
					printScoreSort(list5);
				}
				Collections.sort(list);
				break;
			case DELETE:
				int delete = inputId();
				int deleteReurnValue = dbCon.delete(delete);
				if (deleteReurnValue == 1) {
					System.err.println("���� ����");
				} else {
					System.out.println("���� ����");
				}
				break;

			case EXIT:
				run = false;
				System.err.println("!SYSTEM : ���α׷��� �����մϴ�.");
				break;
			}
		}
	}

	private static void printScoreSort(ArrayList<Character> list) {
		Collections.sort(list, Collections.reverseOrder());
		System.out.println("");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + 1 + "��" + "\t" + list.get(i).toString());
		}
	}

	private static Character updataeCharacter(Character characters) {
		int viability = inputGearData(characters.getName());
		characters.setViability(viability);
		int power = inputGearData(characters.getName());
		characters.setPower(power);
		int intelligence = inputGearData(characters.getName());
		characters.setIntelligence(intelligence);
		characters.calTotal();
		characters.calAvg();
		characters.calGrade();
		System.out.println("�����Ϸ�");
		return characters;
	}

	private static String inputgGender() {
		String gender = null;
		boolean flag = false;
		for (; true;) {
			try {
				System.out.print("����(����/����) >> ");
				gender = sc.nextLine();
				Pattern pattern = Pattern.compile("^[��-�R]{2}$");
				Matcher matcher = pattern.matcher(gender); // (genderNum == 1) ? "����" : "����";
				if (matcher.find() && gender.equals("����") || gender.equals("����")) {
					flag = true;
					break;
				}
				if (flag == false) {
					System.err.println("������ ���� �ʽ��ϴ�.");
				}

			} catch (Exception e) {
				System.err.println("������ ���� �ʽ��ϴ�.");
			}
		}
		return gender;
	}

	private static int inputGearData(String geard) {
		boolean run = true;
		int gear = 0;
		while (run) {
			System.out.print(geard + " " + ">>");
			try {
				gear = Integer.parseInt(sc.nextLine());
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(String.valueOf(gear));
				if (matcher.find() && gear >= 0 && gear <= 100) {
					break;
				} else {
					System.err.println("!SYSTEM : ������ ������ϴ�. �ٽ� �����ϰڽ��ϴ�.");
				}
			} catch (Exception e) {
				System.err.println("!SYSTEM : ���� �Է¿� ������ �߻��Ͽ����ϴ�.");
				gear = 0;
			}
		}
		return gear;
	}

	private static int inputId() {
		boolean run = true;
		int id = 0;
		while (run) {
			try {
				System.out.print("ID �Է� : ");
				id = Integer.parseInt(sc.nextLine());
				if (id > 0 && id < Integer.MAX_VALUE) {
					run = false;
				}
			} catch (NullPointerException e) {
				System.err.println("ID����");
			}
		}
		return id;
	}

	private static String searchCharacter() {
		String name = null;
		name = nameMatchPattern();
		return name;
	}

	private static String nameMatchPattern() {
		String name = null;
		while (true) {
			try {
				System.out.print(" * ĳ���� �̸� : > ");
				name = sc.nextLine();
				Pattern pattern = Pattern.compile("^[��-�R]{1,3}$");
				Matcher matcher = pattern.matcher(name);
				if (matcher.find()) {
					break;
				} else {
					System.err.println("!SYSTEM : ������ �ǹٸ��� �ʽ��ϴ�. �ٽ� �Է����ֽʽÿ�");
				}
			} catch (Exception e) {
				System.err.println("!SYSTEM : ������ �ǹٸ��� �ʽ��ϴ�. �ٽ� �Է����ֽʽÿ�");
			}
		}
		return name;
	}

	private static void analyzeData(ArrayList<Character> list) {
		for (Character data : list) {
			System.out.println(data.getName() + " >> " + "��: " + "\t" + data.getTotal() + "\t" + "���: " + "\t"
					+ String.format("%.2f", data.getAvg()) + "\t" + "���: " + data.getGrede());

		}
	}

	private static void printCharacter(ArrayList<Character> list) {
		System.out.println("id" + "\t" + "�̸�" + "\t" + "���� " + "\t" + "Ŭ���� " + "\t" + "����" + "\t" + "����" + "\t" + "������"
				+ "\t" + "��" + "\t" + "����" + "\t" + "����" + "\t" + "���" + "\t" + "���");
		for (Character data : list) {
			System.out.println(data);
		} // for

	}

	private static Character inputDataCharacter() {
		String name = nameMatchPattern();
		String tribe = inputTribe();
		String myclass = inputMyclass();
		String gender = inputgGender();
		int level = inputGearData("����");
		int viability = inputGearData("������");
		int power = inputGearData("��");
		int intelligence = inputGearData("����");
		Character character = new Character(name, tribe, myclass, gender, level, viability, power, intelligence);
		character.calTotal();
		character.calAvg();
		character.calGrade();
		return character;
	}

	private static String inputMyclass() {
		String myclass = null;
		boolean flag = false;
		for (; true;) {
			try {
				System.out.print(" * Ŭ����(Ÿ��ź/����/����): > ");
				myclass = sc.nextLine();
				Pattern pattern = Pattern.compile("^[��-�R]{1,3}$");
				Matcher matcher = pattern.matcher(myclass);
				if (matcher.find() && myclass.equals("Ÿ��ź") || myclass.equals("����") || myclass.equals("����")) {
					flag = true;
					break;
				}
				if (flag == false) {
					System.err.println("�������� ���� �ʽ��ϴ�.");
				}

			} catch (Exception e) {
				System.err.println("������ ���� �ʽ��ϴ�.");
			}
		}
		return myclass;
	}

	private static String inputTribe() {
		String tribe = null;
		boolean flag = false;
		for (; true;) {
			try {
				System.out.print(" * ����(�ΰ�/����/������/�ΰ���): > ");
				tribe = sc.nextLine();
				Pattern pattern = Pattern.compile("^[��-�R]{1,3}$");
				Matcher matcher = pattern.matcher(tribe);
				if (matcher.find() && tribe.equals("�ΰ�") || tribe.equals("����") || tribe.equals("������")
						|| tribe.equals("�ΰ���")) {
					flag = true;
					break;
				}
				if (flag == false) {
					System.err.println("�������� ���� �ʽ��ϴ�.");
				}

			} catch (Exception e) {
				System.err.println("������ ���� �ʽ��ϴ�.");
			}
		}
		return tribe;

	}

}