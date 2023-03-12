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
					"+========================================[ Ä³¸¯ÅÍ »ý¼ºÇÁ·Î±×·¥ ]========================================+");
			System.out.println("|		 1 ÀÎÀû»çÇ× | 2Ãâ·Â | 3ºÐ¼® | 4°Ë»ö | 5¼öÁ¤ | 6¼øÀ§(ÃÑÇÕ¼ø) | 7»èÁ¦ | 8Å»Ãâ	  	          |");
			System.out.println(
					"+==================================================================================================+");
			System.out.print("> ");
			no = Integer.parseInt(sc.nextLine());
			// switch
			switch (no) {
			case INPUT:
				Character character = inputDataCharacter();
				// DBÀÔ·Â
				int rValue = dbCon.insert(character);
				if (rValue == 1) {
					System.out.println("»ðÀÔ¼º°ø");
				} else {
					System.err.println("»ðÀÔ½ÇÆÐ");
				}
				break;
			case PRINT:
				ArrayList<Character> list2 = dbCon.select();
				if (list2 == null) {
					System.out.println("¼±ÅÃ ½ÇÆÐ!");
				} else {
					printCharacter(list2);
				}
				break;
			case ANALYZE:
				ArrayList<Character> list3 = dbCon.analizeSelect();
				if (list3 == null) {
					System.out.println("¼±ÅÃ ½ÇÆÐ!");
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
					System.err.println("Ä³¸¯ÅÍ ÀÌ¸§ °Ë»ö ¿À·ù");
				}
				break;
			case UPDATE:
				int updateRetrunValue = 0;
				int id = inputId(); // id¸¦ ÅëÇØ¼­ °ª
				Character characters = dbCon.selectId(id);
				if (characters == null) {
					System.out.println("¼öÁ¤¿À·ù");
				} else {
					Character updatecharacter = updataeCharacter(characters);
					updateRetrunValue = dbCon.update(updatecharacter);
				}
				if (updateRetrunValue == 1) {
					System.out.println("UPDATE ¼º°ø");
				} else {
					System.err.println("UPDATE ½ÇÆÐ");
				}
				break;
			case SORT:
				ArrayList<Character> list5 = dbCon.selectSort();
				if (list5 == null) {
					System.err.println("Á¤·Ä ¿À·ù");
				} else {
					printScoreSort(list5);
				}
				Collections.sort(list);
				break;
			case DELETE:
				int delete = inputId();
				int deleteReurnValue = dbCon.delete(delete);
				if (deleteReurnValue == 1) {
					System.err.println("»èÁ¦ ¼º°ø");
				} else {
					System.out.println("»èÁ¦ ½ÇÆÐ");
				}
				break;

			case EXIT:
				run = false;
				System.err.println("!SYSTEM : ÇÁ·Î±×·¥À» Á¾·áÇÕ´Ï´Ù.");
				break;
			}
		}
	}

	private static void printScoreSort(ArrayList<Character> list) {
		Collections.sort(list, Collections.reverseOrder());
		System.out.println("");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + 1 + "µî" + "\t" + list.get(i).toString());
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
		System.out.println("¼öÁ¤¿Ï·á");
		return characters;
	}

	private static String inputgGender() {
		String gender = null;
		boolean flag = false;
		for (; true;) {
			try {
				System.out.print("¼ºº°(³²ÀÚ/¿©ÀÚ) >> ");
				gender = sc.nextLine();
				Pattern pattern = Pattern.compile("^[°¡-ÆR]{2}$");
				Matcher matcher = pattern.matcher(gender); // (genderNum == 1) ? "³²¼º" : "¿©¼º";
				if (matcher.find() && gender.equals("³²ÀÚ") || gender.equals("¿©ÀÚ")) {
					flag = true;
					break;
				}
				if (flag == false) {
					System.err.println("¼ºº°ÀÌ ¸ÂÁö ¾Ê½À´Ï´Ù.");
				}

			} catch (Exception e) {
				System.err.println("¼ºº°ÀÌ ¸ÂÁö ¾Ê½À´Ï´Ù.");
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
					System.err.println("!SYSTEM : ¹üÀ§¸¦ ¹þ¾î³µ½À´Ï´Ù. ´Ù½Ã ½ÃÀÛÇÏ°Ú½À´Ï´Ù.");
				}
			} catch (Exception e) {
				System.err.println("!SYSTEM : Á¡¼ö ÀÔ·Â¿¡ ¿À·ù°¡ ¹ß»ýÇÏ¿´½À´Ï´Ù.");
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
				System.out.print("ID ÀÔ·Â : ");
				id = Integer.parseInt(sc.nextLine());
				if (id > 0 && id < Integer.MAX_VALUE) {
					run = false;
				}
			} catch (NullPointerException e) {
				System.err.println("ID¿À·ù");
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
				System.out.print(" * Ä³¸¯ÅÍ ÀÌ¸§ : > ");
				name = sc.nextLine();
				Pattern pattern = Pattern.compile("^[°¡-ÆR]{1,3}$");
				Matcher matcher = pattern.matcher(name);
				if (matcher.find()) {
					break;
				} else {
					System.err.println("!SYSTEM : ÆÐÅÏÀÌ ¿Ç¹Ù¸£Áö ¾Ê½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ½Ê½Ã¿À");
				}
			} catch (Exception e) {
				System.err.println("!SYSTEM : ÆÐÅÏÀÌ ¿Ç¹Ù¸£Áö ¾Ê½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ½Ê½Ã¿À");
			}
		}
		return name;
	}

	private static void analyzeData(ArrayList<Character> list) {
		for (Character data : list) {
			System.out.println(data.getName() + " >> " + "¹æ¾î±¸: " + "\t" + data.getTotal() + "\t" + "Æò±Õ: " + "\t"
					+ String.format("%.2f", data.getAvg()) + "\t" + "µî±Þ: " + data.getGrede());

		}
	}

	private static void printCharacter(ArrayList<Character> list) {
		System.out.println("id" + "\t" + "ÀÌ¸§" + "\t" + "Á¾Á· " + "\t" + "Å¬·¡½º " + "\t" + "·¹º§" + "\t" + "¼ºº°" + "\t" + "»ýÁ¸·Â"
				+ "\t" + "Èû" + "\t" + "Áö´É" + "\t" + "ÃÑÁ¡" + "\t" + "Æò±Õ" + "\t" + "µî±Þ");
		for (Character data : list) {
			System.out.println(data);
		} // for

	}

	private static Character inputDataCharacter() {
		String name = nameMatchPattern();
		String tribe = inputTribe();
		String myclass = inputMyclass();
		String gender = inputgGender();
		int level = inputGearData("·¹º§");
		int viability = inputGearData("»ýÁ¸·Â");
		int power = inputGearData("Èû");
		int intelligence = inputGearData("Áö´É");
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
				System.out.print(" * Å¬·¡½º(Å¸ÀÌÅº/ÇåÅÍ/¿ö·Ï): > ");
				myclass = sc.nextLine();
				Pattern pattern = Pattern.compile("^[°¡-ÆR]{1,3}$");
				Matcher matcher = pattern.matcher(myclass);
				if (matcher.find() && myclass.equals("Å¸ÀÌÅº") || myclass.equals("ÇåÅÍ") || myclass.equals("¿ö·Ï")) {
					flag = true;
					break;
				}
				if (flag == false) {
					System.err.println("Á¾Á·°ªÀÌ ¸ÂÁö ¾Ê½À´Ï´Ù.");
				}

			} catch (Exception e) {
				System.err.println("¼ºº°ÀÌ ¸ÂÁö ¾Ê½À´Ï´Ù.");
			}
		}
		return myclass;
	}

	private static String inputTribe() {
		String tribe = null;
		boolean flag = false;
		for (; true;) {
			try {
				System.out.print(" * Á¾Á·(ÀÎ°£/¿¢¼Ò/°¢¼ºÀÚ/¹Î°£ÀÎ): > ");
				tribe = sc.nextLine();
				Pattern pattern = Pattern.compile("^[°¡-ÆR]{1,3}$");
				Matcher matcher = pattern.matcher(tribe);
				if (matcher.find() && tribe.equals("ÀÎ°£") || tribe.equals("¿¢¼Ò") || tribe.equals("°¢¼ºÀÚ")
						|| tribe.equals("¹Î°£ÀÎ")) {
					flag = true;
					break;
				}
				if (flag == false) {
					System.err.println("Á¾Á·°ªÀÌ ¸ÂÁö ¾Ê½À´Ï´Ù.");
				}

			} catch (Exception e) {
				System.err.println("¼ºº°ÀÌ ¸ÂÁö ¾Ê½À´Ï´Ù.");
			}
		}
		return tribe;

	}

}