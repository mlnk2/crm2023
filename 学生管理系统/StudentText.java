package 学生管理系统;

import 集合.StundentText01;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentText {
    public static void startStudentSystem() {
        ArrayList<Student> list = new ArrayList<>();

        //给循环取个名字
        loop:
        while (true) {
            System.out.println("----------------欢迎来到学生管理系统----------------");
            System.out.println("1:添加学生");
            System.out.println("2:删除学生");
            System.out.println("3:修改学生");
            System.out.println("4:查询学生");
            System.out.println("5:退出");
            System.out.println("请输入你的选择");
            Scanner sc = new Scanner(System.in);
            String chooes = sc.next();


            switch (chooes) {
                case "1":
                    addStudent(list);
                    break;
                case "2":
                    deleteStudent(list);
                    break;
                case "3":
                    xiuGaiStudent(list);
                    break;
                case "4":
                    chaXueStudent(list);
                    break;
                case "5":
                    System.out.println("退出");
                    //跳出带名字的循环
                    break loop;

                //停止虚拟机运行
                //System.exit(0);
                default:
                    System.out.println("没有这个选项");
            }
        }
    }
    public static void addStudent(ArrayList<Student> list) {
        Student ss = new Student();

        Scanner sc = new Scanner(System.in);
        String id = null;
        while (true){
            System.out.println("请输入学生id");
            id = sc.next();
            boolean jieguo = panduan(list, id);
            if (jieguo) {
                System.out.println("你输入的id重复，请重新输入");
            } else {

                ss.setId(id);
                break;
            }
        }

        System.out.println("请输入学生姓名");
        String name = sc.next();
        ss.setName(name);

        System.out.println("请输入学生年龄");
        int age = sc.nextInt();
        ss.setAge(age);

        System.out.println("请输入学生家庭住址");
        String dizhi = sc.next();
        ss.setDizhi(dizhi);

        list.add(ss);

        System.out.println("学生信息添加成功");

    }

    public static void deleteStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要删除的id");
        String id = sc.next();
       int idex = suoying(list,id);
       if(idex >= 0){
           list.remove(idex);
           System.out.println("id为：" + id + "的学生" + "删除成功");
       }else{
           System.out.println("id不存在，删除失败");
       }

    }

    public static void xiuGaiStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要修改的学生id");
        String id = sc.next();
        int idex = suoying(list ,id);

        if(idex == -1){
            System.out.println("你要修改的" + id + "不存在，请重新输入" );
            return;
        }
        //获取要修改的学生对象
        Student str = list.get(idex);
        System.out.println("请输入要修改的学生名字");
        String newName = sc.next();
        str.setName(newName);

        System.out.println("请输入要修改的学生年龄");
        int newAge = sc.nextInt();
        str.setAge(newAge);

        System.out.println("请输入要修改的学生家庭住址");
        String newDizhi = sc.next();
        str.setDizhi(newDizhi);

        System.out.println("学生信息修改成功");
    }

    public static void chaXueStudent(ArrayList<Student> list) {
        if (list.size() == 0) {
            System.out.println("当前无学生信息，请添加后查询");
            return;
        }
        System.out.println("id\t姓名\t年龄\t家庭住址");
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            System.out.println(stu.getId() + "\t" + stu.getName() + "\t" + stu.getAge() + "\t" + stu.getDizhi());
        }
    }

    public static boolean panduan(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            String sts = stu.getId();
            if (sts.equals(id)) {
                return true;
            }

        }
        return false;
    }
    public static int suoying(ArrayList<Student> list, String id){
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            String sd = stu.getId();
            if(sd.equals(id)){
                return i;
            }
        }
        return -1;
    }
}
