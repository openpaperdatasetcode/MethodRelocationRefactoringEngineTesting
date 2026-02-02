package test;
import java.util.ArrayList;import java.util.List;
private enum SourceEnum {INSTANCE;
class MemberInner {public List<String> overloadedMethod(String input) {List<String> list = new ArrayList<>();list.add(input);return list;}
public List<String> overloadedMethod(Integer input) {List<String> list = new ArrayList<>();list.add(input.toString());return list;}}
static class StaticNested {}
static {INSTANCE.memberInner.method();}
private final MemberInner memberInner = new MemberInner();
protected int moveMethod(ProtectedEnum target, String... args) {int count = 0;for (String arg : args) {target.process(arg);count++;}return count;}
protected class MemberInner {public List<String> method() {return super.toString() != null ? new ArrayList<>() : new ArrayList<>();}}}
/**
Javadoc for protected enum: Contains local inner class and business logic*/protected enum ProtectedEnum {VALUE1, VALUE2;
{class LocalInner {void process(String data) {System.out.println(data);}}new LocalInner().process(name());}
public void process(String input) {}}