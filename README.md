# Hướng Dẫn Chi Tiết Về Regular Expression (Regex) Và Custom Exception Trong Java

Tài liệu này cung cấp lý thuyết chi tiết, ví dụ thực tế và các lưu ý quan trọng về **Regular Expression (Biểu thức chính quy)** và **Cơ chế xử lý lỗi/Custom Exception** trong ngôn ngữ lập trình Java. Đây là tài liệu hướng dẫn học tập và tham khảo được chuẩn hóa phục vụ cho việc push lên GitHub.

---

## Mục Lục
1. [Regular Expression (Regex) là gì?](#1-regular-expression-regex-là-gì)
2. [Chi Tiết Cú Pháp Regex Thông Dụng](#2-chi-tiết-cú-pháp-regex-thông-dụng)
3. [Xử Lý Lỗi (Error & Exception Handling) Trong Java](#3-xử-lý-lỗi-error--exception-handling-trong-java)
4. [Custom Exception (Ngoại Lệ Tự Định Nghĩa)](#4-custom-exception-ngoại-lệ-tự-định-nghĩa)
5. [Demo Thực Tế & Hướng Dẫn Chạy](#5-demo-thực-tế--hướng-dẫn-chạy)

---

## 1. Regular Expression (Regex) là gì?

**Regular Expression (Regex / Biểu thức chính quy)** là một chuỗi ký tự đặc biệt đóng vai trò như một khuôn mẫu (pattern) dùng để tìm kiếm, kiểm tra (validate) hoặc thay thế các chuỗi văn bản khác.

Trong Java, Regex được hỗ trợ mạnh mẽ thông qua:
* Phương thức `String.matches(String regex)` để kiểm tra nhanh một chuỗi có khớp mẫu hay không.
* Thư viện chuyên sâu `java.util.regex` bao gồm hai lớp chính:
  * `Pattern`: Định nghĩa khuôn mẫu Regex (đã biên dịch).
  * `Matcher`: Thực hiện các thao tác so khớp khuôn mẫu với chuỗi dữ liệu.

---

## 2. Chi Tiết Cú Pháp Regex Thông Dụng

### 2.1. Ký hiệu cơ bản & Neo chuỗi (Anchors)
* `^`: Neo bắt đầu chuỗi (ví dụ: `^A` nghĩa là chuỗi phải bắt đầu bằng chữ `A`).
* `$`: Neo kết thúc chuỗi (ví dụ: `Z$` nghĩa là chuỗi phải kết thúc bằng chữ `Z`).
* `.`: Đại diện cho **một ký tự bất kỳ** (ngoại trừ dấu xuống dòng `\n`).

### 2.2. Bộ lượng từ (Quantifiers) - Số lần lặp lại
* `?`: Lặp lại từ **0 đến 1 lần** (tương đương `{0,1}`).
* `*`: Lặp lại từ **0 đến nhiều lần** (tương đương `{0,}`).
* `+`: Lặp lại từ **1 đến nhiều lần** (tương đương `{1,}`).
* `{n}`: Lặp lại đúng **n** lần.
* `{n,m}`: Lặp lại tối thiểu **n** lần và tối đa **m** lần.
* `{n,}`: Lặp lại tối thiểu **n** lần trở lên.

### 2.3. Tập hợp ký tự (Character Classes / Sets)
* `[abc]`: So khớp với 1 ký tự duy nhất, là `a`, `b`, hoặc `c`.
* `[^abc]`: Phủ định - so khớp với bất kỳ ký tự nào **không** phải `a`, `b`, hay `c`.
* `[a-z]`: Khớp với 1 ký tự chữ thường từ `a` đến `z`.
* `[A-Z]`: Khớp với 1 ký tự chữ hoa từ `A` đến `Z`.
* `[A-Za-z]`: Khớp với 1 ký tự chữ thường hoặc chữ hoa.
* `[0-9]`: Khớp với 1 chữ số từ `0` đến `9`.

### 2.4. Ký hiệu viết tắt (Shorthands)
| Ký hiệu | Tương đương với | Mô tả |
| :--- | :--- | :--- |
| `\d` | `[0-9]` | Khớp với 1 chữ số bất kỳ |
| `\D` | `[^0-9]` | Khớp với 1 ký tự không phải là số |
| `\w` | `[a-zA-Z_0-9]` | Khớp với chữ cái, chữ số hoặc dấu gạch dưới |
| `\W` | `[^a-zA-Z_0-9]` | Khớp với ký tự đặc biệt (không thuộc `\w`) |
| `\s` | `[ \t\n\x0B\f\r]` | Khớp với ký tự khoảng trắng (space, tab, xuống dòng...) |
| `\S` | `[^\s]` | Khớp với ký tự không phải khoảng trắng |

### 2.5. Phép Toán OR `|` và Nhóm `()`
* `(abc|xyz)`: So khớp với cả cụm từ `abc` hoặc cụm từ `xyz`.
* > [!WARNING]
  > **Lưu ý tránh nhầm lẫn**: `[abc]` chỉ khớp 1 ký tự đơn, còn `(abc)` khớp một nhóm ký tự. 
  > Ví dụ: `(ko|co)` khớp với "ko" hoặc "co". Trong khi `[ko|co]` lại là tập hợp gồm các ký tự đơn lẻ `k`, `o`, `|`, `c` và chỉ so khớp duy nhất 1 ký tự trong số đó.

### 2.6. Lưu ý đặc biệt trong Java
Trong ngôn ngữ Java, dấu gạch chéo ngược `\` được dùng để escape (thoát chuỗi) cho các ký tự đặc biệt trong chuỗi (ví dụ: `\n`, `\t`, `\"`). Vì vậy, khi viết Regex trong chuỗi Java, bạn cần phải ghi **hai dấu gạch chéo ngược (`\\`)** để trình biên dịch Java hiểu đó là một ký tự `\` của Regex.
* Ví dụ: Trong Regex thông thường viết là `\d{3}`, trong Java String phải viết là `"\\d{3}"`.

---

## 3. Xử Lý Lỗi (Error & Exception Handling) Trong Java

Trong Java, tất cả các tình huống lỗi phát sinh khi chạy ứng dụng được chia thành hai nhóm lớn kế thừa từ lớp `Throwable`:
1. **Error**: Những lỗi hệ thống nghiêm trọng (như `OutOfMemoryError`, `StackOverflowError`). Khi xảy ra Error, chương trình thường sẽ bị dừng hoàn toàn và lập trình viên khó có thể bắt hoặc xử lý nó bằng code.
2. **Exception**: Những sự kiện ngoại lệ xảy ra trong lúc chạy chương trình mà lập trình viên có thể dự phòng và bắt (catch) để xử lý giúp chương trình tiếp tục hoạt động bình thường.

### 3.1. Phân loại lỗi thực tế
* **Compile-time Error (Lỗi biên dịch / Cú pháp)**: Xảy ra khi viết sai cú pháp Java. Trình biên dịch sẽ báo đỏ và ngăn không cho build chương trình. Đây là lỗi dễ kiểm soát nhất.
* **Runtime Error (Lỗi thực thi / Exception)**: Phát sinh khi chương trình đang chạy. Ví dụ: Người dùng nhập chữ thay vì nhập số, chia cho 0 (`ArithmeticException`), truy cập mảng ngoài chỉ số (`ArrayIndexOutOfBoundsException`), tham chiếu đối tượng null (`NullPointerException`).
* **Logic Error (Lỗi logic)**: Chương trình chạy trơn tru, không crash, nhưng kết quả đầu ra sai do tư duy hoặc thuật toán của lập trình viên bị lỗi.

### 3.2. Cơ chế Try-Catch-Finally
* **`try`**: Đóng gói các dòng lệnh có nguy cơ xảy ra ngoại lệ.
* **`catch`**: Bắt các ngoại lệ cụ thể xảy ra trong khối `try` để xử lý một cách an toàn mà không làm sập ứng dụng.
* **`finally`**: Khối lệnh luôn luôn được thực thi (dù có xảy ra ngoại lệ hay không), thường dùng để giải phóng tài nguyên (đóng file, đóng kết nối database, đóng Scanner...).
* **`throw`**: Chủ động ném ra một đối tượng Exception tại bất kỳ vị trí nào trong code.
* **`throws`**: Khai báo ở chữ ký phương thức để cảnh báo rằng phương thức này có thể ném ra các ngoại lệ này và yêu cầu nơi gọi nó phải xử lý.

---

## 4. Custom Exception (Ngoại Lệ Tự Định Nghĩa)

Mặc dù Java cung cấp sẵn rất nhiều lớp Exception (như `IllegalArgumentException`, `NullPointerException`), trong thực tế dự án, chúng ta cần định nghĩa các lỗi mang tính chất **nghiệp vụ đặc thù** (Business Logic) của ứng dụng.

### Cách tạo Custom Exception:
1. Tạo một Class mới kế thừa lớp `Exception` (đối với **Checked Exception** - bắt buộc phải try-catch khi gọi).
2. Hoặc kế thừa từ lớp `RuntimeException` (đối với **Unchecked Exception** - không bắt buộc phải khai báo hoặc bắt trực tiếp).

```java
// Ví dụ về một Custom Exception cho việc validate tuổi
public class InvalidAgeException extends Exception {
    // Constructor không tham số
    public InvalidAgeException() {
        super("Tuổi nhập vào không hợp lệ!");
    }

    // Constructor có truyền thông điệp chi tiết
    public InvalidAgeException(String message) {
        super(message);
    }
}
```

---

## 5. Demo Thực Tế & Hướng Dẫn Chạy

Dưới đây là một chương trình Java hoàn chỉnh kết hợp cả việc kiểm tra dữ liệu bằng **Regex** và bắt lỗi bằng **Custom Exception** cùng cơ chế xử lý bộ đệm của `Scanner` tránh lặp vô hạn.

### 5.1. Mã nguồn Demo
Lưu file này tại đường dẫn [Program.java](file:///d:/Summer2026/PRO192/RegexAndEror/06-customError/src/runtime/Program.java):

```java
package runtime;

import java.util.InputMismatchException;
import java.util.Scanner;

// Định nghĩa Custom Exception
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. DEMO REGEX VALIDATION (So khớp chuỗi)
        System.out.println("=== 1. DEMO REGEX VALIDATION ===");
        
        // Mẫu Regex kiểm tra mã sinh viên FPT (Ví dụ: SE123456789)
        // Bắt đầu bằng S/s, tiếp theo là E/e, theo sau bởi đúng 9 chữ số
        String studentIdRegex = "^[Ss][Ee]\\d{9}$";
        
        String id1 = "SE182736450";
        String id2 = "se999999999";
        String id3 = "HE123";
        
        System.out.println(id1 + " hợp lệ? " + id1.matches(studentIdRegex)); // true
        System.out.println(id2 + " hợp lệ? " + id2.matches(studentIdRegex)); // true
        System.out.println(id3 + " hợp lệ? " + id3.matches(studentIdRegex)); // false
        
        // Mẫu Regex kiểm tra tên hợp lệ (Viết hoa chữ cái đầu của mỗi từ)
        String nameRegex = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$";
        String name1 = "Nguyen Van A";
        String name2 = "nguyen van a";
        System.out.println("\"" + name1 + "\" hợp lệ tên? " + name1.matches(nameRegex)); // true
        System.out.println("\"" + name2 + "\" hợp lệ tên? " + name2.matches(nameRegex)); // false
        
        System.out.println();

        // 2. DEMO EXCEPTION HANDLING & CUSTOM EXCEPTION
        System.out.println("=== 2. DEMO CUSTOM EXCEPTION & SCANNER ===");
        
        int age = 0;
        boolean isValidInput = false;
        
        while (!isValidInput) {
            System.out.print("Nhập tuổi của bạn (10 - 60 tuổi): ");
            try {
                age = sc.nextInt(); // Có thể ném ra InputMismatchException nếu nhập chữ
                
                // Kiểm tra điều kiện nghiệp vụ
                if (age < 10 || age > 60) {
                    throw new InvalidAgeException("Tuổi phải nằm trong phạm vi từ 10 đến 60.");
                }
                
                isValidInput = true; // Dữ liệu hợp lệ, cho phép thoát vòng lặp
                
            } catch (InputMismatchException e) {
                System.out.println("Lỗi: Bạn phải nhập một số nguyên!");
                sc.nextLine(); // QUAN TRỌNG: Dọn dẹp token rác trong buffer Scanner để tránh lặp vô hạn
            } catch (InvalidAgeException e) {
                System.out.println("Lỗi nghiệp vụ: " + e.getMessage());
                // Không cần dọn buffer vì Scanner đã đọc thành công số nguyên, chỉ là giá trị không thỏa mãn logic
            }
        }
        
        System.out.println("Đăng ký thành công! Tuổi của bạn là: " + age);
        sc.close();
    }
}
```

### 5.2. Các điểm mấu chốt được tối ưu trong Demo
1. **Dọn sạch buffer Scanner (`sc.nextLine()`)**: Đặt trong khối catch của `InputMismatchException` để ngăn chặn hiện tượng lặp vô hạn khi người dùng nhập chuỗi ký tự thay vì số nguyên.
2. **Sử dụng Custom Exception (`InvalidAgeException`)**: Giúp chương trình tách biệt rõ ràng giữa lỗi định dạng hệ thống (nhập sai kiểu dữ liệu) và lỗi vi phạm quy tắc nghiệp vụ (tuổi quá nhỏ hoặc quá lớn).
3. **Cải tiến Regex chính xác**: Sửa lại các regex mẫu ở cuối file nguồn gốc, bao gồm việc sửa regex số điện thoại `^0\\d{9}$` và regex tên người `^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$`.
