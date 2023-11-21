package 学生管理系统;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作1：登录 2：注册 3：忘记密码");
            String choose = sc.next();

            switch (choose) {
                case "1":
                    dengLu(list);
                    break;
                case "2":
                    zhuCe(list);
                    break;
                case "3":
                    wanJipassWord(list);
                    break;
                case "4":
                    System.out.println("谢谢使用，再见");
                    System.exit(0);

                default:
                    System.out.println("没有这个选项");
            }

        }


    }

    private static void dengLu(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.println("请输入你的用户名");
            String username = sc.next();

            //判断用户是否存在
            boolean fals = cantions(list, username);
            if (!fals) {
                System.out.println("用户名" + username + "未注册，请先去注册");
                return;
            }

            //输入密码
            System.out.println("请输入你的密码");
            String password = sc.next();

            //输入验证码
            while (true) {
                String rightCode = getCode();
                System.out.println("当前验证码为" + rightCode);
                System.out.println("请输入验证码");
                String Code = sc.next();
                if (Code.equalsIgnoreCase(rightCode)) {
                    System.out.println("验证码正确");
                    break;
                } else {
                    System.out.println("你输入的验证码有误，请重新输入");
                    continue;
                }
            }
            //定义一个方法判断用户名和密码是否正确
            //封装思想的应用：
            //可以把零散的数据，封装成一个对象
            User useInfo = new User(username, password, null, null);
            boolean result = cheakUserInfo(list, useInfo);
            if (result) {
                System.out.println("登录成功，你可以使用学生登陆系统");
                //创建对象调用方法，启动学生管理系统
                StudentText ss = new StudentText();
                ss.startStudentSystem();
                break;
            } else {
                System.out.println("登录失败，你的用户名或密码错误");
            }
            if (i == 2) {
                System.out.println("当前账号" + username + "被锁定,请进行充值");
                return;
            } else {
                System.out.println("用户名或密码错误，你还有" + (2 - i) + "次");
            }

        }

    }

    private static boolean cheakUserInfo(ArrayList<User> list, User useInfo) {
        //遍历集合，判断用户名和密码是否存在；
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getUsername().equals(useInfo.getUsername()) && user.getPassword().equals(useInfo.getPassword())) {
                return true;
            }
        }
        return false;
    }

    private static void wanJipassWord(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的用户名");
        String username = sc.next();
        boolean flas = cantions(list, username);
        if (!flas) {
            System.out.println("当前用户" + username + "未注册，请先注册");
            return;
        }
        //键盘录入身份证号码和手机号码
        System.out.println("请输入身份证号");
        String personID = sc.next();
        System.out.println("请输入手机号");
        String phoneNumber = sc.next();

        //比较用户的手机号和身份证号是否相同
        //需要把对象遍历出来
        int idex = findIdex(list, username);
        User user = list.get(idex);
        if (!(user.getPensonID().equalsIgnoreCase(personID) && user.getPhoneNumber().equalsIgnoreCase(phoneNumber))) {
            System.out.println("身份证或手机号有误，不能修改密码");
            return;
        }
        //代码执行到这，表示数据验证成功，可以修改密码，
        String password;
        while (true) {
            System.out.println("请输入新的密码");
            password = sc.next();
            System.out.println("请确认密码");
            String againpassword = sc.next();
            if (password.equalsIgnoreCase(againpassword)) {
                System.out.println("两次密码输入一致");
                break;
            } else {
                System.out.println("两次密码不一致，请重新输入");
                continue;
            }

        }
        user.setPassword(password);
        System.out.println("密码修改成功");
    }

    private static int findIdex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }


    //用户名验证
    private static void zhuCe(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        String username;
        while (true) {
            System.out.println("请输入用户名");
            username = sc.next();
            boolean flat = cheakUsername(username);
            if (!flat) {
                System.out.println("格式不满足，请重新输入");
                continue;
            }
            //用户名是否唯一
            boolean flat1 = cantions(list, username);
            if (flat1) {
                System.out.println("用户名" + username + "已存在，请重新输入");
            } else {
                System.out.println("用户名" + username + "可用");
                break;
            }
        }

        //密码验证
        String password;
        while (true) {

            System.out.println("请输入你的密码");
            password = sc.next();
            System.out.println("请确认你的密码");
            String Newpassword = sc.next();
            if (!password.equals(Newpassword)) {
                System.out.println("两次密码不一致，请重新输入");
                continue;
            } else {
                System.out.println("两次密码一致，继续录入其他信息");
                break;
            }
        }

        //身份证验证
        String personID;
        while (true) {
            System.out.println("请输入身份证号码");
            personID = sc.next();
            boolean flat2 = cheakPersonID(personID);

            if (flat2) {
                System.out.println("身份证满足要求");
                break;
            } else {
                System.out.println("身份证格式有误，请重新输入");
                continue;
            }
        }
        //手机号码验证
        String phoneNumber;
        while (true) {
            System.out.println("请输入你的手机号码");
            phoneNumber = sc.next();
            boolean flat3 = cheakPhoneNUmber(phoneNumber);
            if (flat3) {
                System.out.println("手机号码满足要求");
                break;
            } else {
                System.out.println("手机号码有误，请重新输入");
                continue;
            }
        }
        //把用户名，密码，身份证，手机号，放进对象
        User u = new User(username, password, personID, phoneNumber);
        //在放进集合
        list.add(u);

        //遍历集合
        printlist(list);
    }

    private static void printlist(ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            System.out.println(user.getUsername() + "," + user.getPassword() + "," + user.getPensonID() + "," + user.getPhoneNumber());
        }
    }

    private static boolean cheakPhoneNUmber(String phoneNumber) {
        //手机号码长度为11
        if (phoneNumber.length() != 11) {
            return false;
        }
        //不能0开头
        //startsWith如果以0开头则返回false
        if (phoneNumber.startsWith("0")) {
            return false;
        }
        //只能全部是数字
        for (int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            //如果不是在这个范围，则返回false；
            if (!(c >= '0' && c <= '9')) {
                return false;
            }

        }
        //如果是循环结束返回true
        return true;
    }

    private static boolean cheakPersonID(String personID) {
        //身份证长度为18
        if (personID.length() != 18) {
            return false;
        }
        //不能以0开头
        if (personID.startsWith("0")) {
            //如果以0开头返回false
            return false;
        }
        //前17位必须是数字
        for (int i = 0; i < personID.length() - 1; i++) {
            char c = personID.charAt(i);
            //如果有一个数不是0-9之间，直接返回false
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        //最后一位可以是数字，也可以是小写x或者大写X
        char endChar = personID.charAt(personID.length() - 1);
        if ((endChar >= '0' && endChar <= '9') || (endChar == 'x') || (endChar == 'X')) {
            return true;
        } else {
            return false;
        }
    }

    //用户名是否存在
    private static boolean cantions(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            String rightname = user.getUsername();
            if (rightname.equals(username)) {
                return true;
            }
        }
        return false;
    }

    //用户名只能是字母加数字
    //循环得到username的每一个字符，如果有一个表示字母或数字，则返回false
    private static boolean cheakUsername(String username) {
        int len = username.length();
        if (len < 3 || len > 15) {
            return false;
        }
        //用户名只能字母加数字
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                return false;
            }
        }

        //不能纯数字
        int count = 0;
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                count++;
                break;
            }
        }


        return count > 0;
    }

    //验证码
    public static String getCode() {
        //创建一个集合添加所有大写和小学字母
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char) ('a' + i));
            list.add((char) ('A' + i));
        }
        StringBuilder sb = new StringBuilder();
        //随机抽取4个字母
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            //获得随机索引
            int idex = r.nextInt(list.size());
            //利用随机索引获得字符
            char c = list.get(idex);
            //把随机索引添加到sb中
            sb.append(c);
        }
        //把随机数字添加到末尾
        int number = r.nextInt(10);
        sb.append(number);

        //把字符串变数组，在数组中修改，在创建新的字符串
        char[] arr = sb.toString().toCharArray();
        //拿最后一个索引，跟随索引指向的元素
        int randomidex = r.nextInt(arr.length);
        //最大索引指向的元素和随机索引指向交换
        char temp = arr[randomidex];
        arr[randomidex] = arr[arr.length - 1];
        arr[arr.length - 1] = temp;
        //返回交换好的数组
        return new String(arr);
    }

}
