import java.util.*;

class Example {
	public final static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (final Exception e) {
			e.printStackTrace();
			// Handle any exceptions.6
		}
	}

	public static int[] sort(int[] xr) {
		for (int x = 0; x < xr.length - 1; x++) {
			for (int i = 0; i < xr.length - (1 + x); i++) {
				if (xr[i] > xr[i + 1]) {
					xr[i] = xr[i] + xr[i + 1];
					xr[i + 1] = xr[i] - xr[i + 1];
					xr[i] = xr[i] - xr[i + 1];
					// And also,
					/*
					 * int t = xr[i];
					 * xr[i] = xr[i+1];
					 * xr[i+1]=t;
					 */

				}
			}
		}

		return xr;
	}

	public static int[] findRank(String[][] data_of_students, int[][] marks_of_students) {
		int[] total = new int[marks_of_students.length];
		int[] total_sort = new int[marks_of_students.length];
		int[] rank = new int[marks_of_students.length];
		for (int i = 0; i < marks_of_students.length; i++) {
			total[i] = marks_of_students[i][0] + marks_of_students[i][1];
			total_sort[i] = marks_of_students[i][0] + marks_of_students[i][1];
		}
		sort(total_sort);
		for (int i = 0; i < marks_of_students.length; i++) {
			for (int x = 0; x < marks_of_students.length; x++) {
				if (total_sort[i] == total[x]) {
					rank[x] = marks_of_students.length - i;
				}
			}
		}
		return rank;
	}

	// [1]
	public static void addNewStudent(String[][] data_of_students, int[][] marks_of_students) {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("|   	                      ADD NEW STUDENTS                             |");
			System.out.println("----------------------------------------------------------------------------");
			String yes_or_no = "Y";
			L1: while (Objects.equals(yes_or_no, "Y") || Objects.equals(yes_or_no, "y")) {
				String[][] temp_array = new String[data_of_students.length + 1][2];
				for (int i = 0; i < data_of_students.length; i++) {
					temp_array[i][0] = data_of_students[i][0]; // Student ID
					temp_array[i][1] = data_of_students[i][1]; // Student Name
				}
				System.out.print("\nEnter Student ID   : ");
				temp_array[data_of_students.length][0] = input.next();
				for (int i = 0; i < data_of_students.length; i++) {
					if (Objects.equals(temp_array[i][0], temp_array[data_of_students.length][0])) {
						System.out.println("The Student ID already exists");
						continue L1;
					}
				}

				System.out.print("Enter Student Name : ");
				temp_array[data_of_students.length][1] = input.next();
				data_of_students = temp_array;

				int[][] temp_marks_array = new int[data_of_students.length][2];
				for (int i = 0; i < marks_of_students.length; i++) {
					temp_marks_array[i] = marks_of_students[i];
				}

				temp_marks_array[temp_marks_array.length - 1][0] = -1;
				temp_marks_array[temp_marks_array.length - 1][1] = -1;

				marks_of_students = temp_marks_array;

				System.out.print("\nStudent has been added successfully. Do you want to add a new student (Y/n): ");
				yes_or_no = input.next();
				if (Objects.equals(yes_or_no, "n") || Objects.equals(yes_or_no, "N")) {
					clearConsole();
					mainMenu(data_of_students, marks_of_students);
				}
			}
		}
	}

	// [2]
	public static void addNewStudentWithMarks(String[][] data_of_students, int[][] marks_of_students) {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("|   	                ADD NEW STUDENTS WITH MARKS                        |");
			System.out.println("----------------------------------------------------------------------------");
			String yes_or_no = "Y";
			L1: while (Objects.equals(yes_or_no, "Y") || Objects.equals(yes_or_no, "y")) {
				String[][] temp_array = new String[data_of_students.length + 1][2];
				for (int i = 0; i < data_of_students.length; i++) {
					temp_array[i][0] = data_of_students[i][0]; // Student ID
					temp_array[i][1] = data_of_students[i][1]; // Student Name
				}
				System.out.print("\nEnter Student ID   : ");
				temp_array[data_of_students.length][0] = input.next();
				for (int i = 0; i < data_of_students.length; i++) {
					if (Objects.equals(temp_array[i][0], temp_array[data_of_students.length][0])) {
						System.out.println("The Student ID already exists");
						continue L1;
					}
				}
				System.out.print("Enter Student Name : ");
				temp_array[data_of_students.length][1] = input.next();
				data_of_students = temp_array;

				int[][] temp_marks_array = new int[data_of_students.length][2];
				for (int i = 0; i < marks_of_students.length; i++) {
					temp_marks_array[i] = marks_of_students[i];
				}
				while (true) {
					System.out.print("\nProgramming Fundamentals Marks   : ");
					int marks = input.nextInt();
					if (marks >= 0 && marks <= 100) {
						temp_marks_array[temp_marks_array.length - 1][0] = marks; // Programming Fundamentals Marks
						break;
					} else {
						System.out.println("Invalid marks, Please enter correct marks.");
					}
				}
				while (true) {
					System.out.print("Database Management System Marks : ");
					int marks = input.nextInt();
					if (marks >= 0 && marks <= 100) {
						temp_marks_array[temp_marks_array.length - 1][1] = marks; // Datamanagement System Marks
						break;
					} else {
						System.out.println("Invalid marks, Please enter correct marks.\n");
					}
				}
				marks_of_students = temp_marks_array;

				System.out.print("\nStudent has been added successfully. Do you want to add a new student (Y/n): ");
				yes_or_no = input.next();
				if (Objects.equals(yes_or_no, "n") || Objects.equals(yes_or_no, "N")) {
					clearConsole();
					mainMenu(data_of_students, marks_of_students);
				}
			}
		}
	}

	// [3]
	public static void addMarks(String[][] data_of_students, int[][] marks_of_students) {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("|   	                          ADD MARKS                                |");
			System.out.println("----------------------------------------------------------------------------");
			String yes_or_no = "Y";

			while (Objects.equals(yes_or_no, "Y") || Objects.equals(yes_or_no, "y")) {
				int count = 0, count2 = 0;
				System.out.print("\nEnter Student ID : ");
				String id = input.next();
				for (int i = 0; i < data_of_students.length; i++) {
					if (Objects.equals(data_of_students[i][0], id)) {
						System.out.println("Student name     : " + data_of_students[i][1]);
						if (marks_of_students[i][0] != -1) {
							System.out.println("This student's marks have been already added.");
							System.out.println("If you want to update the marks, Please use [5] Update Marks option.\n");
							count2--;

						} else {
							while (true) {
								System.out.print("\nProgramming Fundamentals Marks   : ");
								int marks = input.nextInt();
								if (marks >= 0 && marks <= 100) {
									marks_of_students[i][0] = marks; // Programming Fundamentals Marks
									break;
								} else {
									System.out.println("Invalid marks, Please enter correct marks.");
								}
							}

							while (true) {
								System.out.print("Database Management System Marks : ");
								int marks = input.nextInt();
								if (marks >= 0 && marks <= 100) {
									marks_of_students[i][1] = marks; // Datamanagement System Marks
									break;
								} else {
									System.out.println("Invalid marks, Please enter correct marks.\n");
								}
							}
						}
					} else {
						count++;
					}
				}

				if (count == data_of_students.length) {
					System.out.print("Invalid Student ID. Do you want to search again? (Y/n) ");
					yes_or_no = input.next();
				}

				else if (count2 == -1) {
					System.out.print("Do you want to add marks for another student? (Y/n) ");
					yes_or_no = input.next();
				} else {
					System.out.print("Marks have been added. Do you want to add marks for another student? (Y/n) ");
					yes_or_no = input.next();
				}
			}

			if (Objects.equals(yes_or_no, "n") || Objects.equals(yes_or_no, "N")) {
				clearConsole();
				mainMenu(data_of_students, marks_of_students);
			}
		}
	}

	// [4]
	public static void updateStudentDetails(String[][] data_of_students, int[][] marks_of_students) {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("|   	                   UPDATE STUDENT DETAILS                          |");
			System.out.println("----------------------------------------------------------------------------");
			String yes_or_no = "Y";
			while (Objects.equals(yes_or_no, "Y") || Objects.equals(yes_or_no, "y")) {
				int count = 0;
				System.out.print("\nEnter Student ID : ");
				String id = input.next();
				for (int i = 0; i < data_of_students.length; i++) {
					if (Objects.equals(data_of_students[i][0], id)) {
						System.out.println("Student name     : " + data_of_students[i][1]);
						System.out.print("\nEnter the new student name : ");
						data_of_students[i][1] = input.next();

					} else {
						count++;
					}
				}
				if (count == data_of_students.length) {
					System.out.print("Invalid Student ID. Do you want to search again? (Y/n) ");
					yes_or_no = input.next();
				} else {
					System.out.print(
							"\nStudent details has been updated successfully.\nDo you want to Update another student details? (Y/n) ");
					yes_or_no = input.next();
				}
			}
			if (Objects.equals(yes_or_no, "n") || Objects.equals(yes_or_no, "N")) {
				clearConsole();
				mainMenu(data_of_students, marks_of_students);
			}
		}
	}

	// [5]
	public static void updateMarks(String[][] data_of_students, int[][] marks_of_students) {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("|   	                        UPDATE MARKS                               |");
			System.out.println("----------------------------------------------------------------------------");
			String yes_or_no = "Y";

			while (Objects.equals(yes_or_no, "Y") || Objects.equals(yes_or_no, "y")) {
				int count = 0, count2 = 0;
				System.out.print("\nEnter Student ID : ");
				String id = input.next();
				for (int i = 0; i < data_of_students.length; i++) {
					if (Objects.equals(data_of_students[i][0], id)) {
						System.out.println("Student name     : " + data_of_students[i][1]);
						if (marks_of_students[i][0] == -1) {
							System.out.println("\nThis student's marks yet to be added.");
							count2--;
						} else {
							System.out.println("\nProgramming Fundamentals Marks   : " + marks_of_students[i][0]);
							System.out.println("Database Management System Marks : " + marks_of_students[i][1]);

							while (true) {
								System.out.print("\nEnter new Programming Fundamentals Marks   : ");
								int marks = input.nextInt();
								if (marks >= 0 && marks <= 100) {
									marks_of_students[i][0] = marks; // Programming Fundamentals Marks
									break;
								} else {
									System.out.println("Invalid marks, Please enter correct marks.");
								}
							}

							while (true) {
								System.out.print("Enter new Database Management System Marks : ");
								int marks = input.nextInt();
								if (marks >= 0 && marks <= 100) {
									marks_of_students[i][1] = marks; // Datamanagement System Marks
									break;
								} else {
									System.out.println("Invalid marks, Please enter correct marks.\n");
								}
							}
						}
					} else {
						count++;
					}
				}
				if (count == data_of_students.length) {
					System.out.print("Invalid Student ID. Do you want to search again? (Y/n) ");
					yes_or_no = input.next();
				}

				else if (count2 == -1) {
					System.out.print("Do you want to add marks for another student? (Y/n) ");
					yes_or_no = input.next();
				} else {
					System.out
							.print("Marks have been updated successfully.\nDo you want to update marks for another student? (Y/n) ");
					yes_or_no = input.next();
				}
			}

			if (Objects.equals(yes_or_no, "n") || Objects.equals(yes_or_no, "N")) {
				clearConsole();
				mainMenu(data_of_students, marks_of_students);
			}
		}
	}

	// [6]
	public static void deleteStudent(String[][] data_of_students, int[][] marks_of_students) {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("|   	                       DELETE STUDENT                              |");
			System.out.println("----------------------------------------------------------------------------");
			String yes_or_no = "Y";
			int count;
			while (Objects.equals(yes_or_no, "Y") || Objects.equals(yes_or_no, "y")) {
				count = 0;
				String[][] temp_d = new String[data_of_students.length - 1][2];
				int[][] temp_m = new int[marks_of_students.length - 1][2];
				System.out.print("\nEnter Student ID : ");
				String id = input.next();
				for (int i = 0; i < data_of_students.length; i++) {
					if (Objects.equals(data_of_students[i][0], id)) {
						for (int x = 0, y = 0; y < data_of_students.length; x++, y++) {
							if (y == i) {
								x--;
								continue;
							} else {
								temp_d[x][0] = data_of_students[y][0];
								temp_d[x][1] = data_of_students[y][1];
								temp_m[x][0] = marks_of_students[y][0];
								temp_m[x][1] = marks_of_students[y][1];
							}
						}
						data_of_students = temp_d;
						marks_of_students = temp_m;
						count = -1;
						break;
					}
				}
				if (count == 0) {
					System.out.print("Invalid Student ID. Do you want to search again? (Y/n) ");
					yes_or_no = input.next();
				} else {
					System.out
							.print("Student has been deleted successfully.\nDo you want to Update another student details? (Y/n) ");
					yes_or_no = input.next();
				}
			}
			if (Objects.equals(yes_or_no, "n") || Objects.equals(yes_or_no, "N")) {
				clearConsole();
				mainMenu(data_of_students, marks_of_students);
			}
		}
	}

	// [7]
	public static void printStudentDetails(String[][] data_of_students, int[][] marks_of_students) {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("|   	                    PRINT STUDENT DETAILS                          |");
			System.out.println("----------------------------------------------------------------------------");
			String yes_or_no = "Y";
			while (Objects.equals(yes_or_no, "Y") || Objects.equals(yes_or_no, "y")) {
				int count = 0;
				System.out.print("\nEnter Student ID : ");
				String id = input.next();
				for (int i = 0; i < data_of_students.length; i++) {
					if (Objects.equals(data_of_students[i][0], id)) {
						System.out.println("Student name     : " + data_of_students[i][1]);
						if (marks_of_students[i][0] == -1) {
							System.out.println("\nmarks yet to be added.\n");
						} else {
							int total = marks_of_students[i][0] + marks_of_students[i][1];
							double avg = (marks_of_students[i][0] + marks_of_students[i][1]) / 2.0;
							int[] rank = findRank(data_of_students, marks_of_students);
							System.out.println("+---------------------------------+--------------------+");
							System.out.print("|Programming Fundamentals Marks   |\t\t   ");
							if (marks_of_students[i][0] == 100) {
								System.out.print(marks_of_students[i][0]);
								System.out.println(" |");
							} else if (marks_of_students[i][0] > 9) {
								System.out.print(" " + marks_of_students[i][0]);
								System.out.println(" |");
							} else {
								System.out.print("  " + marks_of_students[i][0]);
								System.out.println(" |");
							}
							System.out.print("|Database Management System Marks |\t\t   ");
							if (marks_of_students[i][1] == 100) {
								System.out.print(marks_of_students[i][1]);
								System.out.println(" |");
							} else if (marks_of_students[i][1] > 9) {
								System.out.print(" " + marks_of_students[i][1]);
								System.out.println(" |");
							} else {
								System.out.print("  " + marks_of_students[i][1]);
								System.out.println(" |");
							}
							System.out.print("|Total Marks                      |\t\t   ");
							if (total >= 100) {
								System.out.print(total);
								System.out.println(" |");
							} else if (total > 9) {
								System.out.print(" " + total);
								System.out.println(" |");
							} else {
								System.out.print("  " + total);
								System.out.println(" |");
							}
							System.out.print("|Avg. Marks                       |\t\t ");
							if (avg == 100) {
								System.out.print(avg);
								System.out.println(" |");
							} else if (avg > 9) {
								System.out.print(" " + avg);
								System.out.println(" |");
							} else {
								System.out.print("  " + avg);
								System.out.println(" |");
							}
							System.out.print("|Rank                             |\t\t ");
							if (rank[i] >= 100) {
								System.out.print("  " + rank[i]);
								System.out.println(" |");
							} else if (rank[i] > 9) {
								System.out.print("   " + rank[i]);
								System.out.println(" |");
							} else {
								System.out.print("    " + rank[i]);
								System.out.println(" |");
							}
							System.out.println("+---------------------------------+--------------------+\n");

						}
					} else {
						count++;
					}
				}
				if (count == data_of_students.length) {
					System.out.print("Invalid Student ID. Do you want to search again? (Y/n) ");
					yes_or_no = input.next();
				}

				else {
					System.out.print("Do you want to search another student details? (Y/n) ");
					yes_or_no = input.next();
				}
			}

			if (Objects.equals(yes_or_no, "n") || Objects.equals(yes_or_no, "N")) {
				clearConsole();
				mainMenu(data_of_students, marks_of_students);
			}
		}
	}

	// [8]
	public static void printStudentRanks(String[][] data_of_students, int[][] marks_of_students) {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("|   	                     PRINT STUDENT RANKS                           |");
			System.out.println("----------------------------------------------------------------------------\n\n");
			System.out.println("+-------+-------+-------------------------------+-------------+------------+");
			System.out.println("| Rank  | ID    | Name                          | Total Marks | Avg. Marks |");
			System.out.println("+-------+-------+-------------------------------+-------------+------------+");
			int[] rank = findRank(data_of_students, marks_of_students);
			for (int i = 0; i < data_of_students.length; i++) {
				for (int x = 0; x < data_of_students.length; x++) {
					if (marks_of_students[x][0] == -1) {
						continue;
					} else if (rank[x] == (i + 1)) {
						int total = marks_of_students[x][0] + marks_of_students[x][1];
						double avg = (marks_of_students[x][0] + marks_of_students[x][1]) / 2.0;
						if (rank[x] > 100) {
							System.out.print("| " + rank[x] + "   ");
						} else if (rank[x] > 9) {
							System.out.print("| " + rank[x] + "    ");
						} else {
							System.out.print("| " + rank[x] + "     ");
						}

						System.out.print("| " + data_of_students[x][0] + "  ");
						System.out.print("| " + data_of_students[x][1] + "                        ");

						if (total > 100) {
							System.out.print("|         " + total + " ");
						} else if (total > 9) {
							System.out.print("|          " + total + " ");
						} else {
							System.out.print("|           " + total + " ");
						}
						if (avg == 100) {
							System.out.print("|      " + avg + " |");
						} else if (total > 9) {
							System.out.print("|       " + avg + " |");
						} else {
							System.out.print("|        " + avg + " |");
						}
						System.out.println();
					}
				}
			}

			System.out.println("+-------+-------+-------------------------------+-------------+------------+");
			System.out.print("\n\nDo you want to back to main menu? (Y/n) ");
			String yes_or_no = input.next();
			if (Objects.equals(yes_or_no, "Y") || Objects.equals(yes_or_no, "y")) {
				clearConsole();
				mainMenu(data_of_students, marks_of_students);
			}
		}
	}

	// [9]
	public static void bestInProgrammingFundamentals(String[][] data_of_students, int[][] marks_of_students) {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("|   	              BEST IN PROGRAMMING FUNDAMENTALS                     |");
			System.out.println("----------------------------------------------------------------------------\n\n");
			System.out.println("+-------+----------------------------------+----------+------------+");
			System.out.println("| ID    | Name                             | PF Marks | DBMS Marks |");
			System.out.println("+-------+----------------------------------+----------+------------+");
			int[] pf = new int[marks_of_students.length];
			for (int i = 0; i < marks_of_students.length; i++) {
				pf[i] = marks_of_students[i][0];
			}
			sort(pf);

			for (int i = marks_of_students.length - 1; i >= 0; i--) {
				for (int x = 0; x < marks_of_students.length; x++) {
					if (marks_of_students[x][0] == -1) {
						continue;
					} else if (i > 0 && pf[i - 1] == pf[i]) {
						continue;
					} else if (pf[i] == marks_of_students[x][0]) {

						System.out.print("| " + data_of_students[x][0] + "  ");
						System.out.print("| " + data_of_students[x][1] + "                           ");

						if (marks_of_students[x][0] == 100) {
							System.out.print("|      " + marks_of_students[x][0] + " ");
						} else if (marks_of_students[x][0] > 9) {
							System.out.print("|       " + marks_of_students[x][0] + " ");
						} else {
							System.out.print("|        " + marks_of_students[x][0] + " ");
						}
						if (marks_of_students[x][1] == 100) {
							System.out.print("|        " + marks_of_students[x][1] + " |");
						} else if (marks_of_students[x][1] > 9) {
							System.out.print("|         " + marks_of_students[x][1] + " |");
						} else {
							System.out.print("|          " + marks_of_students[x][1] + " |");
						}
						System.out.println();
					}
				}
			}

			System.out.println("+-------+----------------------------------+----------+------------+");
			System.out.print("\n\nDo you want to back to main menu? (Y/n) ");
			String yes_or_no = input.next();
			if (Objects.equals(yes_or_no, "Y") || Objects.equals(yes_or_no, "y")) {
				clearConsole();
				mainMenu(data_of_students, marks_of_students);
			}
		}
	}

	// [10]
	public static void bestInDatamanagementSystem(String[][] data_of_students, int[][] marks_of_students) {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("|   	              BEST IN DATABASE MANAGEMENT SYSTEM                   |");
			System.out.println("----------------------------------------------------------------------------\n\n");
			System.out.println("+-------+----------------------------------+------------+----------+");
			System.out.println("| ID    | Name                             | DBMS Marks | PF Marks |");
			System.out.println("+-------+----------------------------------+------------+----------+");
			int[] dbms = new int[marks_of_students.length];
			for (int i = 0; i < marks_of_students.length; i++) {
				dbms[i] = marks_of_students[i][1];
			}
			sort(dbms);

			for (int i = marks_of_students.length - 1; i >= 0; i--) {
				for (int x = 0; x < marks_of_students.length; x++) {
					if (marks_of_students[x][1] == -1) {
						continue;
					} else if (i > 0 && dbms[i - 1] == dbms[i]) {
						continue;
					} else if (dbms[i] == marks_of_students[x][1]) {

						System.out.print("| " + data_of_students[x][0] + "  ");
						System.out.print("| " + data_of_students[x][1] + "                           ");

						if (marks_of_students[x][1] == 100) {
							System.out.print("|        " + marks_of_students[x][1] + " ");
						} else if (marks_of_students[x][1] > 9) {
							System.out.print("|         " + marks_of_students[x][1] + " ");
						} else {
							System.out.print("|          " + marks_of_students[x][1] + " ");
						}
						if (marks_of_students[x][0] == 100) {
							System.out.print("|      " + marks_of_students[x][0] + " |");
						} else if (marks_of_students[x][0] > 9) {
							System.out.print("|       " + marks_of_students[x][0] + " |");
						} else {
							System.out.print("|        " + marks_of_students[x][0] + " |");
						}
						System.out.println();
					}
				}
			}

			System.out.println("+-------+----------------------------------+----------+------------+");
			System.out.print("\n\nDo you want to back to main menu? (Y/n) ");
			String yes_or_no = input.next();
			if (Objects.equals(yes_or_no, "Y") || Objects.equals(yes_or_no, "y")) {
				clearConsole();
				mainMenu(data_of_students, marks_of_students);
			}
		}
	}

	public static void mainMenu(String[][] data_of_students, int[][] marks_of_students) {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("|   	          WELCOME TO GDSE MARKS MANAGEMENT SYSTEM                  |");
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("\n[1] Add New Student\t\t\t[2] Add New Student With Marks");
			System.out.println("[3] Add Marks\t\t\t\t[4] Update Student Details");
			System.out.println("[5] Update Marks\t\t\t[6] Delete Student");
			System.out.println("[7] Print Student Details\t\t[8] Print Student Ranks");
			System.out.println("[9] Best in Programming Fundamentals\t[10] Best in Datamanagement System");
			System.out.print("\nEnter an option to continue > ");
			int option = input.nextInt();
			switch (option) {
				case 1:
					clearConsole();
					addNewStudent(data_of_students, marks_of_students);
					break;

				case 2:
					clearConsole();
					addNewStudentWithMarks(data_of_students, marks_of_students);
					break;

				case 3:
					clearConsole();
					addMarks(data_of_students, marks_of_students);
					break;

				case 4:
					clearConsole();
					updateStudentDetails(data_of_students, marks_of_students);
					break;

				case 5:
					clearConsole();
					updateMarks(data_of_students, marks_of_students);
					break;

				case 6:
					clearConsole();
					deleteStudent(data_of_students, marks_of_students);
					break;

				case 7:
					clearConsole();
					printStudentDetails(data_of_students, marks_of_students);
					break;

				case 8:
					clearConsole();
					printStudentRanks(data_of_students, marks_of_students);
					break;

				case 9:
					clearConsole();
					bestInProgrammingFundamentals(data_of_students, marks_of_students);
					break;

				case 10:
					clearConsole();
					bestInDatamanagementSystem(data_of_students, marks_of_students);
					break;

			}
		}
	}
	public static void main(String[] args) {
		String[][] data_of_students = new String[0][2];
		int[][] marks_of_students = new int[0][2];
		mainMenu(data_of_students, marks_of_students);
	}
}
