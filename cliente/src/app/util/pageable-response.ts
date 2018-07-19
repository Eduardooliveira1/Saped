export class PageableResponse<T>{
    count: number;
    data: T[];

    constructor() {
        this.count = 0;
        this.data = [];
    }
}