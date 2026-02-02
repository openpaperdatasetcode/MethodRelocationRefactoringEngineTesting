import java.util.List;import java.util.ArrayList;
interface Processable {}
/**
Abstract source class implementing interface with required features*/public abstract class SourceClass implements Processable {private String value = "source_value";
static class StaticNested {}
public void process(TargetClass target) {// Type declaration statementTypeDeclaration typeDecl = new TypeDeclaration();
// With bounds: generic type with upper boundList<? extends CharSequence> boundedList = new ArrayList<>();boundedList.add(value);
int option = 2;// Switch case statementswitch (option) {case 1:target.StaticNested.processData(boundedList);break;case 2:// Expression statementString processed = target.instanceMethod(boundedList.get(0));boundedList.set(0, processed);// Variable call + access instance methodtarget.StaticNested.processData(boundedList);break;default:break;}
// Local inner classclass LocalInner {void execute() {// Lambda expression: () -> System.out.println(this.value)Runnable lambda = () -> System.out.println(SourceClass.this.value);lambda.run();}}
new LocalInner().execute();}
class TypeDeclaration {}}
/**
Javadoc for TargetClass
Abstract target class with static nested class for data processing*/public abstract class TargetClass {public static class StaticNested {public static <T extends CharSequence> void processData(List<T> data) {data.forEach(System.out::println);}}
public String instanceMethod(String input) {return input + "_processed";}}