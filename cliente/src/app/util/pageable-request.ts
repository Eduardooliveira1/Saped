import {DataTable} from 'primeng/primeng';

export class Pageable {
  page: number;
  size: number;
  sort: string;

  constructor(first: DataTable);
  constructor(first: number, second: number);
  constructor(first: any, second?: number) {
    if (first instanceof DataTable) {
      this.page = Math.round(first.first / first.rows);
      this.size = first.rows;
    } else  {
      this.page = Math.round(first);
      this.size = second;
    }
  }

  setPage(page: number) {
    if (page && page === 0) {
      this.page = 0;
    } else {
      this.page = page / this.size;
    }
  }

  setSize(size: number) {
    this.size = (size ? size : 10);
  }

  setSort(sortOrder: number, sortField: string) {
    const direction = sortOrder === 1 ? 'ASC' : 'DESC';
    this.sort = sortField + ',' + direction;
  }


}