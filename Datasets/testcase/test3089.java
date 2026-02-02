import java.util.ArrayList;import java.util.List;import java.util.stream.Collectors;
interface DataProcessor {Object processField(String field);}
public record SourceClass(String sourceData) implements DataProcessor {static class StaticNested {}
class MemberInner {String assist(TargetClass target) {return target.innerField + "_assisted";}}
/**
Method javadoc: Processes multiple TargetClass instances, collects inner field values
@param targets Varargs of TargetClass to process
@return List of processed inner field strings*/public List<String> process(TargetClass... targets) {TypeDeclaration typeDecl = new TypeDeclaration();MemberInner innerHelper = new MemberInner();
return List.of(targets).stream().map(target -> {// Variable call + access instance fieldString rawValue = target.innerField;// Overriding method reference (collection operations pos)Object processed = DataProcessor.super::processField;// Access target's member inner classString targetInnerValue = target.new TargetInner().process(rawValue);return innerHelper.assist(target) + "_" + targetInnerValue;}).collect(Collectors.toList());}
@Overridepublic Object processField(String field) {return field.toUpperCase();}
class TypeDeclaration {}}
public record TargetClass(String targetData) {String innerField = targetData + "_inner";
class TargetInner {String process(String input) {return input + "_target_processed";}}}