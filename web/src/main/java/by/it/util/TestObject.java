package by.it.util;

/**
 * Created by Zoltan on 03.06.2016.
 */
public class TestObject {
    private String hello = "hello";

    public TestObject() {
    }

    @Invoke(repeat = 10)
    public void sayHello() {
        System.out.println(hello);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestObject that = (TestObject) o;

        return !(hello != null ? !hello.equals(that.hello) : that.hello != null);

    }

    @Override
    public int hashCode() {
        return hello != null ? hello.hashCode() : 0;
    }

    public String getHello() {

        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
