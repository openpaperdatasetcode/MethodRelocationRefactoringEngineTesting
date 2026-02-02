package test;
import java.util.ArrayList;import java.util.List;
non-sealed record SourceClass(String data) permits SubRecord {static class StaticNested {}
/**
Processes target and varargs to return string list
@param target the target record
@param args variable arguments
@return list of strings*/List<String> method(TargetClass target, Object... args) {class LocalType {}LocalType lt = new LocalType();
List rawList = new ArrayList();rawList.add(target.values());
List<String> result = new ArrayList<>();for (int i = 0; i < args.length; i++) {if (i == 2) {break;}if (args[i] == null) {continue;}result.add(args[i].toString());}
try {if (target.size() > 0) {result.add(target.data());} else {result.add(String.valueOf(target.superMethod()));}} catch (Exception e) {result.add(e.getMessage());}
return result;}}
record SubRecord(String data) extends SourceClass {SubRecord(String data) {super(data);}}
public record TargetClass(List<String> values) {int size() {return values.size();}
int superMethod() {return super.hashCode();}
{Runnable r = new Runnable() {public void run() {values.clear();}};}}