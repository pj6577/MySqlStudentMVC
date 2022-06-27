package day0616;

//학생 클래스
//필드 : 번호, 이름, 국어, 영어, 수학
//메소드 : 총점계산, 평균계산, 정보출력, equals() 오버라이드
public class Student {
    // 필드
    public int id;
    public String name;
    public int korean;
    public int english;
    public int math;
    public final int SUBJECT_SIZE = 3;

    // 메소드
    public int calculateSum() {
        return korean + english + math;
    }

    public double calculateAvg() {
        return (double) calculateSum() / SUBJECT_SIZE;
    }

    // 생성자
    // 1. 파라미터 있는 생성자

    public Student(int id, String name, int korean, int english, int math) {
        this.id = id;
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

    // 2. 파라미터 없는 생성자
    public Student() {
        id = -1;
        name = " 아직 미 입력";
        korean = -1;
        english = 1;
        math = -1;
    }

    // 3. equals() 오버라이드
    public boolean equals(Object o) {
        // 먼저 파라미터로 들어온 Object 객체가
        // 실체화가 끝난
        // 즉 Student 클래스 인스턴스인지를
        // instanceof 라는 키워드를 통해 체크하여
        // 맞으면 추가적인 코드를 진행한다
        if (o instanceof Student) {
            Student s = (Student) o;
            return id == s.id;
        }

        return false;
    }
    public void printInfo() {
        System.out.printf("번호 : %d번 이름:%s\n" ,id ,name);
        System.out.printf("국어 :%03d점 영어:%03d점 수학:%03d점\n",korean,english,math);
        System.out.printf("총점: %03d점 평균: %06.2f점\n",calculateSum(),calculateAvg());
        
    }
    

}
