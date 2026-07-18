package runtime;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        //Regex: Regular express | pattern | biểu thức chính quy
        //Chuỗi tiêu chuẩn 
        String regex1 = "^[Ss][Ee]\\d{9}$";//=> REGEX
        System.out.println("SE123456789".matches(regex1));
        //1. KÝ TỰ CỐ ĐỊNH
        System.out.println("name".matches("name"));//true
        System.out.println("nAme".matches("name"));//false
        /*-----------------------------------------------------*/
        //2. không phân biệt hoa thường(?i)
        System.out.println("nAme".matches("(?i)name"));//true
        /*-----------------------------------------------------*/
        //3. một kí tự bất kỳ (trừ dấu enter) ' . '
        System.out.println("fname".matches(".name"));//true
        System.out.println("\nfname".matches(".name"));//false
        /*-----------------------------------------------------*/
        //4. lập lại từ 0 đến nhiều lần ký tự trước đó ' *  '
        System.out.println("".matches("me*y"));// my mey meey
        /*-----------------------------------------------------*/
        //5. group: ()
        /*-----------------------------------------------------*/
        //6. lặp lại từ 0 - 1 lần: ?
        System.out.println("".matches("me?y"));
        /*-----------------------------------------------------*/
        //7. + là từ 1 trở lên 
        System.out.println("".matches("me+y"));
        /*-----------------------------------------------------*/
        //8. SET: tập hợp  là []
        System.out.println("may".matches("m[abc]y"));// true
        System.out.println("mey".matches("m[abc]y"));//false
        System.out.println("mey".matches("m[A-Za-z]y"));//true
        //[A-Z]: là chữ hoa
        //[a-z]: là 1 chữ thường
        //[A-Za-z]: là 1 chữ hoa hoặc 1 chữ thường
        /*-----------------------------------------------------*/
        //9. Ngược lại của SET: thêm ^
        System.out.println("mey".matches("m[^abc]y"));// true
        System.out.println("may".matches("m[^abc]y"));//false
        /*-----------------------------------------------------*/
        //10. Khớp 1 chữ số [0 - 9]
        /*-----------------------------------------------------*/
        //11.biên độ giao động lập
        System.out.println("may".matches("me{2,4}y"));
        //{4} : lặp lại 4 lần
        //{1,3}: lặp lại từ 1 đến 3 lần
        //{2,}: lặp lại từ 2 đến trở lên
        //{0,1} ==> ?
        //{1,} ==> +
        //{0,} ==> *
        /*-----------------------------------------------------*/
        //12. OR: |
        //vd: toi (ko|co) gay ==> toi ko gay | toi co gay: true
        // sai lầm là : toi [ko|co] gay ==> [] là lấy một trong các ký tự trong ngoặc vuông
        /*-----------------------------------------------------*/
        //13. ShortHand
        // \w: [a-zA-Z0-9_]  \W: phủ định của \w
        // \d: [0 - 9]       \D: 1 ký tự khác số
        // \s: space         \S: 1 ký tự trừ space
        /*-----------------------------------------------------*/
        //14. ^ và $
        // ^: lấy chữ bắt đầu 
        // $: dấu để chọn chữ để kết thúc 
        /*-----------------------------------------------------*/
        //15.\ hay \\(trong java)
        // \: phế vai trò của một kí tự và trở thành kí tự bình thường
        /*-----------------------------------------------------*/
        // Try catch
        //Error compilation:Lỗi diễn ra trong quá trình biên dịch ==> kiểm soát được
        /* Thường diễn ra khi sai về mặt syntax ==> newbie mới qua lập trình mới */
        
        // Error Runtime : Lỗi phát sinh trong quá trình chạy code ==> không kiểm soát được
        /*Lỗi về mặt nhập vào hệ thống ==> được tạo bởi người dùng ==> người chịu trách nghiệm là dev*/
        
        // Error Logics : Lỗi phát thảo tư duy ==> kiểm soát đươc
        /*Lỗi về mặt tư duy và logic ==> dev*/
        /*-----------------------------------------------------*/
        //Scanf(): là hàm scan(quét để tìm) phục vụ cho việc nhập
        // trong java thì k có hàm, nhưng mà sẽ có object chuyên chứa method chuyên chứa method hỗ trợ scanf
        // tạo ra thằng đó
        Scanner sc = new Scanner(System.in);// trong Scanner là một file để quét
        int age = 0;
        System.out.println("Nhập Age đi:");
        try{
            age = sc.nextInt();
            if(age < 10 || age > 60){
                throw new Exception();
            }
        }catch(Exception e){
            System.out.println("làm lại đê");
        }
        System.out.println("tuổi nè "+ age);
    }
    
}
// ép người dùng nhập tên đúng: ^[A-Z][a-z]*(\\s[A-Z][a-z]*)*;
// ép người dùng nhập email đúng:
// ép người dùng nhập số điện thoại đúng:^0\\d{9}{9}$;