package test;
protected class SourceClass {private TargetClass.InnerRec innerRec;
/**
Converts elements to string list
@param input Input list
@return List of strings
@throws IllegalArgumentException if input is null
*/
final List<String> convert(List<?> input) throws IllegalArgumentException {
super.toString();
TargetClass.InnerRec temp = new TargetClass.InnerRec() {
@Override
void process() {}
};
if (input == null) {
throw new IllegalArgumentException();
}
List<String> result = new ArrayList<>();
try {
for (Object obj : input) {
result.add(obj.toString());
}
innerRec.process();
} catch (Exception e) {
e.printStackTrace();
}
return result;
}
}
public class TargetClass<T> {public class InnerRec {void process() {}}
final List<String> convert(List<Integer> input) {}}