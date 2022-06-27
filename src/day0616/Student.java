package day0616;

//�л� Ŭ����
//�ʵ� : ��ȣ, �̸�, ����, ����, ����
//�޼ҵ� : �������, ��հ��, �������, equals() �������̵�
public class Student {
    // �ʵ�
    public int id;
    public String name;
    public int korean;
    public int english;
    public int math;
    public final int SUBJECT_SIZE = 3;

    // �޼ҵ�
    public int calculateSum() {
        return korean + english + math;
    }

    public double calculateAvg() {
        return (double) calculateSum() / SUBJECT_SIZE;
    }

    // ������
    // 1. �Ķ���� �ִ� ������

    public Student(int id, String name, int korean, int english, int math) {
        this.id = id;
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

    // 2. �Ķ���� ���� ������
    public Student() {
        id = -1;
        name = " ���� �� �Է�";
        korean = -1;
        english = 1;
        math = -1;
    }

    // 3. equals() �������̵�
    public boolean equals(Object o) {
        // ���� �Ķ���ͷ� ���� Object ��ü��
        // ��üȭ�� ����
        // �� Student Ŭ���� �ν��Ͻ�������
        // instanceof ��� Ű���带 ���� üũ�Ͽ�
        // ������ �߰����� �ڵ带 �����Ѵ�
        if (o instanceof Student) {
            Student s = (Student) o;
            return id == s.id;
        }

        return false;
    }
    public void printInfo() {
        System.out.printf("��ȣ : %d�� �̸�:%s\n" ,id ,name);
        System.out.printf("���� :%03d�� ����:%03d�� ����:%03d��\n",korean,english,math);
        System.out.printf("����: %03d�� ���: %06.2f��\n",calculateSum(),calculateAvg());
        
    }
    

}
