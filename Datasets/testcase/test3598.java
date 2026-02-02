package test;
import java.io.IOException;import java.util.List;
protected enum SourceEnum {INSTANCE1, INSTANCE2;
private TargetEnum targetField;private String rawTypeField;
public class MemberInner {String getInnerData() {return "innerData";}}
@FunctionalInterfacepublic interface AbstractMethodInterface {int abstractMethod(TargetEnum target, String arg);}
static {public abstract class StaticAbstractHelper {public abstract int abstractMethod(TargetEnum target, String arg);}}
@Deprecatedpublic SourceEnum() throws IOException {super();targetField = TargetEnum.VALUE1;rawTypeField = new List() {public int size() {return 0;}
public boolean isEmpty() {return true;}
public boolean contains(Object o) {return false;}
public java.util.Iterator iterator() {return null;}
public Object[] toArray() {return new Object[0];}
public Object[] toArray(Object[] a) {return a;}
public boolean add(Object e) {return false;}
public boolean remove(Object o) {return false;}
public boolean containsAll(java.util.Collection c) {return false;}
public boolean addAll(java.util.Collection c) {return false;}
public boolean addAll(int index, java.util.Collection c) {return false;}
public boolean removeAll(java.util.Collection c) {return false;}
public boolean retainAll(java.util.Collection c) {return false;}
public void clear() {}
public Object get(int index) {return null;}
public Object set(int index, Object element) {return null;}
public void add(int index, Object element) {}
public Object remove(int index) {return null;}
public int indexOf(Object o) {return -1;}
public int lastIndexOf(Object o) {return -1;}
public java.util.ListIterator listIterator() {return null;}
public java.util.ListIterator listIterator(int index) {return null;}
public java.util.List subList(int fromIndex, int toIndex) {return null;}}.toString();
MemberInner inner = new MemberInner();String innerData = inner.getInnerData();
switch (targetField) {case VALUE1:rawTypeField += "value1";break;case VALUE2:rawTypeField += "value2";break;}
AbstractMethodInterface abstractInterface = (target, arg) -> targetField.getCode();int result = abstractInterface.abstractMethod(targetField, innerData);
new Runnable() {public void run() {System.out.println(targetField.getLabel());}}.run();}}
private enum TargetEnum {VALUE1(1, "Label1"), VALUE2(2, "Label2");
private final int code;private final String label;
TargetEnum(int code, String label) {this.code = code;this.label = label;
new Runnable() {public void run() {System.out.println("Target anonymous inner class");}};}
int getCode() {return code;}
String getLabel() {return label;}}