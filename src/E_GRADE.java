import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class E_GRADE
{
    public static ArrayList<Students> StudentList = new ArrayList<>();
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        boolean bool = true;
        while (bool)
        {
            bool = E_GRADE.PrintMenu(sc);
        }
    }

    //вивід меню користувача
    public static boolean PrintMenu (Scanner sc)
    {
        int choice;
        //пропонуємо обрати дію, яку хоче зробити користувач
        do{
            System.out.print("1)Add new student\n");
            System.out.print("2)Add list of subjects for current student\n");
            System.out.print("3)Show list of subject for current student\n");
            System.out.print("4)Remove student from list of students\n");
            System.out.print("5)Remove subject from list of subject\n");
            System.out.print("6)Show list of students\n");
            System.out.print("7)Quit\n");
            System.out.println("What would you like to do?");
            choice = sc.nextInt();
            if (choice < 1 || choice > 6)
            {
                System.out.println("Error. Try again");
            }
        }while (choice < 1 || choice > 6);

        //після, зробленого вибору переходемо до конструкції switch case для виклику відповідних функцій
        switch (choice)
        {
            case 1:
                E_GRADE.AddStudent(sc); // додавання студентів
                break;
            case 2:
                E_GRADE.UpdateStudSubList(sc);// додавання предметів для конкретного студента
                break;
            case 3:
                E_GRADE.ShowSubList(sc); // вивід список предметів
                break;
            case 4:
                E_GRADE.RemoveStud(sc);// видалення студента із списку
                break;
            case 5:
                E_GRADE.RemoveSub(sc); // видалення студента зі списку
                break;
            case 6:
                for (Students s : StudentList)
                {
                    s.PrintStudInfo(); // вивід списку студентів
                }
                break;
            case 7:
                sc.nextLine(); // вихід
                break;
        }

        // меню виводиться до поки користувач не захоче вийти, в такому випадку функція повертає
        // false тоді робота циклу зупиняється
        boolean boo;
        if (choice == 6)
        {
            boo = false;
        }
        else
        {
            boo = true;
        }
        return boo;
    }

    //додаємо студента до списку
    public static void AddStudent(Scanner sc)
    {
        String studFname;
        String studSname;
        int studAge;
        String nameOFgroup;
        char choice;

        //вводимо ім'я студента
        do {
            sc.nextLine();
            do {
                System.out.println("Enter first name of student: ");
                studFname = sc.nextLine();
                if (studFname.isEmpty()) {
                    System.out.println("Invalid. Try again.");
                }
            } while (studFname.isEmpty());

            //вводимо прізвище
            do {
                System.out.println("Enter last name of student: ");
                studSname = sc.nextLine();
                if (studSname.isEmpty()) {
                    System.out.println("Invalid. Try again.");
                }
            } while (studSname.isEmpty());

            //вводимо вік
            do {
                System.out.println("Enter age of student: ");
                studAge = sc.nextInt();
                sc.nextLine();
                if (studAge <= 0 || studAge >= 100) {
                    System.out.println("Invalid. Try again.");
                }
            } while (studAge <= 0 || studAge >= 100);

            //вводимо назву групи
            do {
                System.out.println("Enter name of group: ");
                nameOFgroup = sc.nextLine();
                if (nameOFgroup.isEmpty()) {
                    System.out.println("Invalid. Try again.");
                }
            } while (nameOFgroup.isEmpty());

            //створюємо об'єкт класса Students та додаємо до списку студентів
            Students newStudent = new Students(studFname, studSname, studAge, nameOFgroup);
            StudentList.add(newStudent);

            //запитуємо чи хоче користувач продовжувати додавати нових студентів
            System.out.println("Enter '+' to continue, enter '-' to stop adding: ");
            choice = sc.next().charAt(0);


            if (choice == '+')
            {
                continue;
            }
            else if (choice == '-')
            {
                break;
            }

        }while (true);
    }

    //пошук студента для якого треба додати список предметів
    public static void UpdateStudSubList(Scanner sc)
    {
        String adding_StudID;

        //запитуємо id студента до якого хочемо додати список предметів
        sc.nextLine();
        do {
            System.out.print("Enter id of student: \n");
            adding_StudID = sc.nextLine();

            if (adding_StudID.isEmpty())
            {
                System.out.println("Invalid. Try again.");
            }
        }while (adding_StudID.isEmpty());

        //пошук за id
        for (Students s : StudentList)
        {
            //коли знаходимо id викликаємо функцію що додає створює об'єкт класу Subject та додає до списку предметів студента
            if(s.GetStudID().equals(adding_StudID))
            {
                E_GRADE.AddSubjects(s, sc);
                break;
            }
        }
    }

    // додавання предметів
    public static void AddSubjects (Students uStud, Scanner sc)
    {
        String teacher_name;
        String new_subject;
        int new_grade;
        char choice;

        do {
            sc.nextLine();
            //вводимо повне ім'я студента
            do {
                System.out.println("Enter teacher name: ");
                teacher_name = sc.nextLine();

                if (teacher_name.isEmpty())
                {
                    System.out.println("Invalid. Try again.");
                }
            } while (teacher_name.isEmpty());
            //вводимо назву предмету
            do {
                System.out.println("Enter name of subject: ");
                new_subject = sc.nextLine();

                if(new_subject.isEmpty())
                {
                    System.out.println("Invalid. Try again.");
                }
            }while (new_subject.isEmpty());

            //вводимо оцінку за предмет
            do {
                System.out.println("Enter grade for this subject: ");
                new_grade = sc.nextInt();
                sc.nextLine();

                if (new_grade < 0 || new_grade > 100)
                {
                    System.out.println("Invalid. Try again.");
                }
            }while (new_grade < 0 || new_grade > 100);

            //створюємо об'єкт классу Subject та викликаємо метод об'єкта uStud класу Students,
            //що виконує додавання предмету до списку
            Subject new_sub = new Subject(teacher_name, new_subject, new_grade);
            uStud.addSub(new_sub);

            //запит на додавання предметів далі
            System.out.println("If you want continue, enter '+', else enter '-' to stop adding");
            choice = sc.next().charAt(0);


            if (choice == '+')
            {
                continue;
            }
            else if (choice == '-')
            {
                break;
            }
        }while (true);
    }

    //вивід список придметів студента
    public static void ShowSubList (Scanner sc)
    {
        String studID;

        //ввод id студента
        sc.nextLine();
        do {
            System.out.print("Enter id of student: \n");
            studID = sc.nextLine();

            if (studID.isEmpty())
            {
                System.out.println("Invalid. Try again.");
            }
        }while (studID.isEmpty());

        //пошук за id студента та виклик метода класу Students для виводу списку студентів
        for (Students s : StudentList)
        {
            if(s.GetStudID().equals(studID))
            {
                s.ListOFsub();
                break;
            }
        }
    }

    //видалення студента зі списку
    public static void RemoveStud (Scanner sc)
    {
        String studID;

        //вводимо id студента, що хочемо видалити
        sc.nextLine();
        do {
            System.out.print("Enter id of student: \n");
            studID = sc.nextLine();

            if (studID.isEmpty())
            {
                System.out.println("Invalid. Try again.");
            }
        }while (studID.isEmpty());

        //пошук студента за id та видалення зі списку
        int i = 0;
        for (Students s : StudentList)
        {
            if(s.GetStudID().equals(studID))
            {
                StudentList.remove(i);
                System.out.println();
                for (Students x : StudentList)
                {
                    x.PrintStudInfo();
                }
                break;
            }
            i++;
        }
    }

    //видалення предмету
    public static void RemoveSub (Scanner sc)
    {
        String studID;

        //вводимо id студента, щоб видалити предмет зі списку предметів конкретного студента
        sc.nextLine();
        do {
            System.out.print("Enter id of student: \n");
            studID = sc.nextLine();

            if (studID.isEmpty())
            {
                System.out.println("Invalid. Try again.");
            }
        }while (studID.isEmpty());

        //пошук студента за id
        int i = 0;
        for (Students s : StudentList)
        {
            if(s.GetStudID().equals(studID))
            {
                String r_subID;

                //вводимо та шукаємо предмет за його id
                sc.nextLine();
                do {
                    System.out.print("Enter id of subject: \n");
                    r_subID = sc.nextLine();

                    if (r_subID.isEmpty())
                    {
                        System.out.println("Invalid. Try again.");
                    }
                }while (r_subID.isEmpty());

                s.RemoveFromSubList(r_subID);// виклик методу класу Students, що видаляє предмет
                break;
            }
            i++;
        }
    }
}
