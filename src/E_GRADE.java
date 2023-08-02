import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class E_GRADE
{
    public static ArrayList<Account> ALL_Accounts = new ArrayList<>();
    public static ArrayList<Account> StudentAccList = new ArrayList<>();
    public static ArrayList<Account> TeacherAccList = new ArrayList<>();
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int choice;
        boolean bool = true;
        do {
            System.out.println("Welcome to E-GRADE. What you want to do?");
            System.out.println("1)Create account\n" + "2)Log in\n" + "3)Quit");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice)
            {
                case 1:
                    E_GRADE.CreateAcc(sc);
                    break;
                case 2:
                    E_GRADE.LogIN(sc);
                    break;
                case 3:
                    bool = false;
                    break;
            }


        }while (bool);
    }

    public static void CreateAcc (Scanner sc)
    {
        boolean boo = true;
        String u_Fname;
        String u_Lname;
        int u_age;
        String u_password;
        int choice_status;

        do {
            System.out.println("Enter your status (1-teacher, 2-student):");
            choice_status = sc.nextInt();
            sc.nextLine();

            if(choice_status != 1 && choice_status != 2)
            {
                System.out.println("Invalid. Try again.");
            }
        }while (choice_status != 1 && choice_status != 2);

        String u_status = " ";

        switch (choice_status)
        {
            case 1:
                u_status = "teacher";
                break;
            case 2:
                u_status = "student";
                break;
        }

        do {
            System.out.println("Enter your first name:");
            u_Fname = sc.nextLine();

            if (u_Fname.isEmpty())
            {
                System.out.println("Invalid. Try again.");
            }
        }while (u_Fname.isEmpty());

        do {
            System.out.println("Enter your last name:");
            u_Lname = sc.nextLine();

            if (u_Lname.isEmpty())
            {
                System.out.println("Invalid.Try again.");
            }
        }while (u_Lname.isEmpty());

        do {
            System.out.println("Enter your age:");
            u_age = sc.nextInt();
            sc.nextLine();

            if (u_age < 0 || u_age > 100)
            {
                System.out.println("Invalid. Try again.");
            }
        }while (u_age < 0 || u_age > 100);

        do {
            System.out.println("Enter new password:");
            u_password = sc.nextLine();

            if (u_password.isEmpty() || u_password.length() < 6 || u_password.length() > 12)
            {
                System.out.println("Invalid. Try again.");
            }
        }while (u_password.isEmpty() || u_password.length() < 6 || u_password.length() > 12);

        if (u_status.equals("teacher"))
        {
            Account newAcc = new Account(u_status, u_Fname, u_Lname,u_age, u_password);

            E_GRADE.TeacherAccList.add(newAcc);
            E_GRADE.ALL_Accounts.add(newAcc);

            System.out.println("Your personal id: " + newAcc.GetAccId());
            while (boo)
            {
                boo = E_GRADE.PrintTeacherMenu(sc);
            }
        }
        else if (u_status.equals("student"))
        {
            String u_group;
            do {
                System.out.println("Enter name of group:");
                u_group = sc.nextLine();

                if (u_group.isEmpty())
                {
                    System.out.println("Invalid. Try again.");
                }
            }while (u_group.isEmpty());

            Account newAcc = new Account(u_status, u_Fname, u_Lname, u_age, u_group, u_password);

            E_GRADE.StudentAccList.add(newAcc);
            E_GRADE.ALL_Accounts.add(newAcc);

            System.out.println("Your personal id: " + newAcc.GetAccId());

            while (boo)
            {
                boo = E_GRADE.PrintStudentMenu(newAcc, sc);
            }
        }
    }

    //авторизація
    public static void LogIN (Scanner sc)
    {
        String log_id;
        String log_pass;

        //вводимо id
        System.out.println("Enter id to log in:");
        log_id = sc.nextLine();

        //вводимо пароль
        System.out.println("Enter password to log in:");
        log_pass = sc.nextLine();

        for (Account a : E_GRADE.ALL_Accounts)
        {
            //шукаємо на відповідність у списку акаунтів
            if (a.GetAccId().equals(log_id) && a.GetPass().equals(log_pass))
            {
                if (a.GetStatus().equals("teacher"))
                {
                    E_GRADE.PrintTeacherMenu(sc);
                    break;
                }
                else if (a.GetStatus().equals("student"))
                {
                    E_GRADE.PrintStudentMenu(a, sc);
                    break;
                }
            }
        }


    }

    //вивід меню користувача для вчителя
    public static boolean PrintTeacherMenu (Scanner sc)
    {
        int choice;
        //пропонуємо обрати дію, яку хоче зробити користувач
        do{
            System.out.println("1)Show list of students");
            System.out.println("2)Show list of subject for current student");
            System.out.println("3)Rate a student");
            System.out.println("4)Quit");
            System.out.println("What would you like to do?");
            choice = sc.nextInt();
            if (choice < 1 || choice > 4)
            {
                System.out.println("Error. Try again");
            }
        }while (choice < 1 || choice > 4);

        //після, зробленого вибору переходемо до конструкції switch case для виклику відповідних функцій
        switch (choice)
        {
            case 1:
                for (Account a : StudentAccList)
                {
                    a.PrintAccInfo(); // вивід списку студентів
                }
                break;
            case 2:
                E_GRADE.ShowSubList(sc); // вивід список предметів
                break;
            case 3:
                E_GRADE.RateGrade(sc);
                break;
            case 4:
                sc.nextLine(); // вихід
                break;
        }

        // меню виводиться до поки користувач не захоче вийти, в такому випадку функція повертає
        // false тоді робота циклу зупиняється
        boolean boo;
        if (choice == 4)
        {
            boo = false;
        }
        else
        {
            boo = true;
        }
        return boo;
    }

    //вивід меню користувача для студента
    public static boolean PrintStudentMenu (Account theStud, Scanner sc)
    {
        int choice;

        boolean boo;
        do {
            System.out.println("1)Show list of subjects");
            System.out.println("2)Add subjects");
            System.out.println("3)Remove subjects");
            System.out.println("4)Show list of teacher");
            System.out.println("5)Quit");
            System.out.println("What would you like to do?");

            choice = sc.nextInt();
            if (choice < 1 || choice > 5)
            {
                System.out.println("Error. Try again");
            }
        }while (choice < 1 || choice > 5);

        switch (choice)
        {
            case 1:
                theStud.ListOFsub();
                break;
            case 2:
                E_GRADE.AddSubjects(theStud, sc);
                break;
            case 3:
                E_GRADE.Removing(theStud, sc);
                break;
            case 4:
                for (Account a : TeacherAccList)
                {
                    a.PrintAccInfo();
                }
                break;
            case 5:
                sc.nextLine();
                break;
        }

        if (choice == 5)
        {
            boo = false;
        }
        else
        {
            boo = true;
        }

        return boo;
    }

    // додавання предметів
    public static void AddSubjects (Account uStud, Scanner sc)
    {
        String teacher_name;
        String new_subject;
        char choice;

        do {
            sc.nextLine();
            //вводимо повне ім'я вчителя
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

            //створюємо об'єкт классу Subject та викликаємо метод об'єкта uStud класу Account,
            //що виконує додавання предмету до списку
            Subject new_sub = new Subject(teacher_name, new_subject);
            uStud.AddSub(new_sub);

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
        for (Account a : StudentAccList)
        {
            if(a.GetAccId().equals(studID))
            {
                a.ListOFsub();
                break;
            }
        }
    }

    //видалення предметів
    public static void Removing (Account theStud, Scanner sc)
    {
        //викликаєм ф-ю що виводить список поточних предметів
        theStud.ListOFsub();
        String subID;

        char choice;

        do {
            sc.nextLine();
            //вводимо id предмети який хочему видалити
            do {
                System.out.println("Enter id subject to remove from list:");
                subID = sc.nextLine();

                if (subID.isEmpty()) {
                    System.out.println("Invalid. Try again.");
                }
            } while (subID.isEmpty());

            //викликаємо ф-ю що видаляє предмети зі списку передаючи id предмети
            theStud.RemoveFromSubList(subID);

            //запит на додавання предметів далі
            System.out.println("If you want continue, enter '+', else enter '-' to stop removing");
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

    //оцінювання предметів
    public static void RateGrade (Scanner sc)
    {
        char choice;


        do {
            String curID;
            //вводимо id студента якому будемо ставити оцюнки
            do {
                System.out.println("Enter id student to rate:");
                curID = sc.nextLine();

                if (curID.isEmpty())
                {
                    System.out.println("Invalid. Try again.");
                }
            }while (curID.isEmpty());

            //шукаємо цього студента в списку

            for (Account a : E_GRADE.StudentAccList)
            {
                if (a.GetAccId().equals(curID))
                {
                    a.SetGrade(sc);//функція, що виставляє оцінки
                }
            }

            //запит на продовження оцінювання
            System.out.println("If you want continue, enter '+', else enter '-' to stop rating");
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
}
