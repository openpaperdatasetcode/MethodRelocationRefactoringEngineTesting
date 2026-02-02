package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
enum SourceEnum {INSTANCE;
protected int outerProtected = 10;
// First member inner classpublic class SourceInnerOne {}
// Second member inner classpublic class SourceInnerTwo {}
public int instanceMethod(TargetEnum<String>.InnerRec innerRec) throws Exception {// Expression statementint value = innerRec.id() + outerProtected;
// Variable callvalue += innerRec.data().length();
// Access outer protectedvalue += SourceEnum.INSTANCE.outerProtected;
// Switch caseswitch (innerRec.id()) {case 1:value *= 2;break;case 2:value *= 3;break;default:value *= 1;}
// Used by reflectionMethod recMethod = TargetEnum.InnerRec.class.getMethod("id");value += (int) recMethod.invoke(innerRec);
// Override violation (calling final method)String invalid = innerRec.finalMethod();
// Exception handling with abstract synchronized methodtry {List<String> processed = new AbstractProcessor().process(innerRec);value += processed.size();} catch (Exception e) {throw new Exception("Processing failed", e);}
return value;}
// Abstract synchronized methodprivate abstract class AbstractProcessor {synchronized List<String> process(TargetEnum<String>.InnerRec rec) throws Exception {return new TargetEnum.Processor().process(rec);}}
// Static code block with parent class call methodstatic {List<String> initData = new ParentClass().getData();System.out.println("Static init: " + initData);}}
public enum TargetEnum<T> {VALUE;
// Type parameter usageprivate T data;
// Member inner classpublic class InnerClass {public T getValue() {return data;}}
// Inner recordpublic record InnerRec(int id, T data) {public final String finalMethod() {return "Final: " + data;}}
// Processor classpublic static class Processor {public List<String> process(InnerRec rec) {List<String> result = new ArrayList<>();result.add(rec.data().toString());return result;}}}
class ParentClass {// Accessor method for call methodpublic List<String> getData() {return List.of("parent_data");}}