package com.source;
import com.target.TargetEnum;import java.util.Arrays;import java.util.List;
public enum SourceEnum {INSTANCE;
static class StaticNested {}
{new Runnable() {@Overridepublic void run() {}};}
public void process(TargetEnum target) {class LocalInner {private LocalInner() {int val = TargetEnum.STATIC_FIELD;if (val == 1) {System.out.println("Static field is 1");}}}
new LocalInner();TargetEnum.MemberInner inner = target.new MemberInner();
List<Object> list = Arrays.asList();list.forEach(TargetEnum::convert);
inner.setValue(5);Object result = inner.get();}}
package com.target;
enum TargetEnum {VALUE;
static int STATIC_FIELD = 1;
class MemberInner {private int value;
public void setValue(int val) {this.value = val;}
public Object get() {return value;}}
static Object convert(Object obj) {return obj.toString();}}