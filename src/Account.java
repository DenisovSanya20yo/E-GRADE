import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Account
{
    private String AccID;

    private String status;

    private String user_Fname;

    private String user_Lname;

    private int user_age;

    private String Acc_password;

    private String user_group;

    private ArrayList<Subject> subjects;

    public Account (String status, String user_Fname, String user_Lname, int user_age, String Acc_password)
    {
        this.AccID = Get_new_ID();

        this.status = status;
        this.user_Fname = user_Fname;
        this.user_Lname = user_Lname;
        this.user_age = user_age;
        this.Acc_password = Acc_password;
    }
    public Account (String status, String user_Fname, String user_Lname, int user_age, String user_group, String Acc_password)
    {
        this.AccID = Get_new_ID();

        this.status = status;
        this.user_Fname = user_Fname;
        this.user_Lname = user_Lname;
        this.user_age = user_age;

        this.user_group = user_group;

        this.Acc_password = Acc_password;

        this.subjects = new ArrayList<>();
    }

    public String Get_new_ID ()
    {
        String newId;
        //String currID = GetAccId();
        Random rand = new Random();
        boolean nonUnique;

        do {
            newId = ""; // створюємо порожній рядок в який додаємо випадкові цифри за допомогою класу Random
            for (int i = 0; i < 6; i++)
            {
                newId += ((Integer)rand.nextInt(10)).toString();
            }

            nonUnique = false;



            //перебераємо список студентів для перевірки id на унікальність
            for (Account a : E_GRADE.ALL_Accounts)
            {
                if(newId.compareTo(a.GetAccId()) == 0)
                {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);

        return newId;
    }

    public String GetAccId ()
    {
        return this.AccID;
    }

    public void AddSub (Subject aSub)
    {
        this.subjects.add(aSub);
    }

    public void ListOFsub () // вивід списку студентів
    {
        System.out.println("List of subjects: ");

        for (Subject s : this.subjects)
        {
            System.out.println("Teacher: " + s.GetTeacher() + "; " + "Subject: " +
                    s.GetSubjectName() + "; " + "Grade for exame: " + s.GetGrade() + "; " +
                    "ID: " + s.GetSubID() + ";");
        }
        System.out.println();
    }

    public void PrintAccInfo () // виводить інформацію про студетів
    {
        System.out.println("First name: " + this.user_Fname);
        System.out.println("Last name: " + this.user_Lname);
        System.out.println("Age: " + this.user_age);
        if (this.status.equals("student"))
        {
            System.out.println("Group: " + this.user_group);
        }
        System.out.println("ID: " + this.AccID);
        System.out.println();
    }

    public void RemoveFromSubList (String r_subID) // видаляє предмет зі списку
    {
        int i = 0;
        for (Subject s : this.subjects)
        {
            if(s.GetSubID().equals(r_subID))
            {
                this.subjects.remove(i);
                break;
            }
            i++;
        }
        ListOFsub();
    }

    public String GetStatus ()
    {
        return this.status;
    }

    public String GetPass ()
    {
        return this.Acc_password;
    }

    public void SetGrade (Scanner sc)
    {
        char choice;
        do {
            String r_subID;
            do {
                System.out.println("Enter id of subject to rate:");
                r_subID = sc.nextLine();

                if (r_subID.isEmpty())
                {
                    System.out.println("Invalid. Try again.");
                }
            }while (r_subID.isEmpty());

            for (Subject s : this.subjects)
            {
                if (s.GetSubID().equals(r_subID))
                {
                    int set_grade;
                    System.out.println("Enter a grade:");
                    set_grade = sc.nextInt();
                    sc.nextLine();

                    s.SetGrade(set_grade);
                    break;
                }
            }

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
