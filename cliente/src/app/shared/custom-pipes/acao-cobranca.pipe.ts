
import { Pipe, PipeTransform } from "@angular/core";

@Pipe({name: "AcaoCobranca"})
export class AcaoCobranca implements PipeTransform{
    transform(value: string) {
        if(value == "EMITIR") {
            return "Emitir";
        } else if (value == "SEGUNDAVIA") {
            return "2ª Via";
        } else {return "";}
    }

}