package test;
import java.io.IOException;import java.lang.reflect.Constructor;import java.lang.reflect.InvocationTargetException;
public class SourceClass {private String outerPrivateField = "sourcePrivateField";
class SourceMemberInner {}
private SourceClass(TargetClass target) {TypeDeclared typeDecl = new TypeDeclared();
try {String varCall = target.targetField;String privateAccess = outerPrivateField;
if (varCall == null) {throw new NullPointerException("Target field is null");}if (privateAccess.isEmpty()) {throw new IOException("Private field is empty");}} catch (NullPointerException | IOException e) {}}
void methodWithLocalClass() {class SourceLocalInner {void invokeSourceConstructor() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {Constructor<SourceClass> constructor = SourceClass.class.getDeclaredConstructor(TargetClass.class);constructor.setAccessible(true);constructor.newInstance(new TargetClass());}}}}
protected class TargetClass {String targetField = "targetInstanceField";
class TargetMemberInner {}}
class TypeDeclared {}