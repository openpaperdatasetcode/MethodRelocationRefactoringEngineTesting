package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.io.IOException;
public class TargetClass {String targetField;static class TargetStaticNested {}}
private class SourceClass<T> extends ParentClass {class MemberInner {}
public SourceClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
strictfp List<String> methodToMove(TargetClass target) throws IOException { // requires_throwsList<String> result = new ArrayList<>();
// Type declaration statementabstract class CharProcessor {abstract char process();}
// CharacterLiteral with numbers:2CharProcessor processor = new CharProcessor() {@Overridechar process() {return 'a' + 'b'; // Two character literals: 'a', 'b'}};result.add(String.valueOf(processor.process()));
// Super keywordssuper.toString();
// Variable call + Access instance fieldString var = target.targetField;result.add(var);
// Access outer protected (inherited from ParentClass)int protectedVar = this.parentProtectedField;result.add(String.valueOf(protectedVar));
// Used by reflectiontry {Method method = TargetClass.class.getMethod("toString");method.invoke(target);} catch (Exception e) {throw new IOException("Reflection failed", e);}
return result;}}
class ParentClass {protected int parentProtectedField = 10;}