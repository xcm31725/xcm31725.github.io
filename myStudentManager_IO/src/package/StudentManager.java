
package package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * 这是我的学生管理系统
 * 
 * 新建学生类
 * 创建测试类
	 * 1、主界面的编写
	 * 2、查询学生信息
	 * 3、添加学生信息
	 * 4、删除学生信息
	 * 5、查询学生信息
	 * 6、退出
 * 
 * */
public class StudentManagerTest {

	public static void main(String[] args) throws IOException {
		//创建集合对象，用来接收学生信息
		//ArrayList<Student> array = new ArrayList<Student>();
		
		//创建文件路径
		String fileName = "student.txt";
		
		//使用循环来实现重复1、2、3、4动作
		while(true){
			System.out.println("---------欢迎来到学生管理系统---------");
			System.out.println("1、查询学生信息");
			System.out.println("2、添加学生信息");
			System.out.println("3、删除学生信息");
			System.out.println("4、修改学习信息");
			System.out.println("5、退出");
			
			System.out.print("请输入你的选择：");
			//键盘录入
			Scanner sc = new Scanner(System.in);
			String choiceString = sc.nextLine();
			
			//判断走哪个分支
			switch(choiceString){
			case "1":
				//查看学生信息
				findAllStudent(fileName);
				break;
			case "2":
				//添加学生信息
				addStudent(fileName);
				break;
			case "3":
				//删除学生信息
				deleteStudent(fileName);
				break;
			case "4":
				//修改学生信息
				updateStudent(fileName);
				break;
			case "5":
			default:
				System.out.println("欢迎你使用");
				System.exit(0);//结束JVM
			}
		}
	}

	//读取文件中的数据
	public static void readerData(String fileName,ArrayList<Student> array) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		String line;
		while((line=br.readLine())!=null){
			String[] str = line.split(",");
			
			Student s = new Student();
			
			s.setId(str[0]);
			s.setName(str[1]);
			s.setAge(str[2]);
			s.setAddress(str[3]);
			
			array.add(s);
		}
		
		br.close();
	}
	
	//把集合的数据写入文件
	public static void writerData(String fileName,ArrayList<Student> array) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		
		for (int i = 0; i < array.size(); i++) {
			Student s = array.get(i);
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(s.getId()).append(",").append(s.getName()).append(",").append(s.getAge()).append(",").append(s.getAddress());
			
			bw.write(sb.toString());
			
			bw.newLine();
			bw.flush();
		}
		
		bw.close();
	}
	
	//查询学生信息
	public static void findAllStudent(String fileName) throws IOException{
		ArrayList<Student> array = new ArrayList<Student>();
		
		readerData(fileName,array);
		
		if(array.size()==0){
			System.out.println("不好意思，没有你要查询的学生信息，请回去从新输入");
			return;
		}
		
		System.out.println("学号\t姓名\t年龄\t地址");
		for(int i=0;i<array.size();i++){
			Student s = array.get(i);
			System.out.println(s.getId()+"\t"+s.getName()+"\t"+s.getAge()+"\t"+s.getAddress());
		}
	}
	
	//添加学生信息
	public static void addStudent(String fileName) throws IOException{
		ArrayList<Student> array = new ArrayList<Student>();
		
		readerData(fileName,array);
		
		Scanner sc = new Scanner(System.in);
		
		String id;
		
		while(true){
			System.out.println("请输入学生的学号：");
			id = sc.nextLine();
			
			//定义标记
			boolean flag = false;
			for (int i = 0; i < array.size(); i++) {
				Student s = array.get(i);
				
				if(s.getId().equals(id)){
					flag = true;
					break;
				}
			}
			
			if(flag){
				System.out.println("你输入的学号被占用，请从新输入你的学号");
			}else{
				break;
			}
		}
		
		System.out.println("请输入学生的姓名：");
		String name = sc.nextLine();
		
		System.out.println("请输入学生的年龄：");
		String age = sc.nextLine();
		
		System.out.println("请输入学生的地址：");
		String address = sc.nextLine();
		
		//创建学生对象
		Student s = new Student();
		
		//获取学生的信息
		s.setId(id);
		s.setName(name);
		s.setAge(age);
		s.setAddress(address);
		
		//吧学生对象添加到集合中
		array.add(s);
		
		writerData(fileName,array);
		
		System.out.println("添加成功");
		
	}
	
	//删除学生信息
	public static void deleteStudent(String fileName) throws IOException{
		ArrayList<Student> array = new ArrayList<Student>();
		
		readerData(fileName,array);
		
		//用来判断集合中有没有学生信息
		if(array.size()==0){
			System.out.println("不好意思，没有你要删除的学生信息，请回去从新输入");
			return;
		}
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("请输入要删除学生的学号：");
		String id = sc.nextLine();
		
		//用来接收和判断学生对象的所用
		int index = -1;
		for (int i = 0; i < array.size(); i++) {
			Student s = array.get(i);
			
			if(s.getId().equals(id)){
				index = i;
				break;
			}
		}
		
		if(index == -1){
			System.out.println("不好意思，没有你要删除的学生信息，请回去继续你的选择");
		}else{
			array.remove(index);
			writerData(fileName,array);
			System.out.println("删除成功");
		}
	}	

	//修改学生信息
	public static void updateStudent(String fileName) throws IOException{
		
		ArrayList<Student> array = new ArrayList<Student>();
		
		readerData(fileName,array);
		
		//用来判断集合中有没有学生信息
		if(array.size()==0){
			System.out.println("不好意思，没有你要修改的学生信息，请回去从新输入");
			return;
		}
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("请输入你要修改的学生学号：");
		String id = sc.nextLine();
		
		int index = -1;
		for (int i = 0; i < array.size(); i++) {
			Student s = array.get(i);
			
			if(s.getId().equals(id)){
				index = i;
				break;
			}
		}
		
		if(index == -1){
			System.out.println("不好意思，没有知道要修改的学生信息，请回去继续你的选择");
		}else{
			System.out.println("请输入你的新名字：");
			String name = sc.nextLine();
			
			System.out.println("请输入你的新年龄：");
			String age = sc.nextLine();
			
			System.out.println("请输入你的新地址：");
			String address = sc.nextLine();
			
			Student s = new Student();
			
			s.setId(id);
			s.setName(name);
			s.setAge(age);
			s.setAddress(address);
			
			array.set(index, s);
			
			writerData(fileName,array);
			
			System.out.println("修改成功");
		}
	}

}

