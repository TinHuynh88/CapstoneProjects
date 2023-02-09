# ProductavailableUi

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 10.1.0.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

## Install mat-icon

Run 'ng add @angular/material'
In app.module.ts make sure you have: 
	import { MatIconModule } from '@angular/material/icon';
	imports: [..., MatIconModule ]

## Install Tooltips

Run 'npm i ng2-tooltip-directive --save'
In app.module.ts add: 
	import { TooltipModule } from 'ng2-tooltip-directive';
	imports: [..., TooltipModule ]

## Install Pagination

Run 'npm install ngx-pagination --save'
In app.module.ts add: 
	import { NgxPaginationModule } from 'ngx-pagination';
	imports: [..., NgxPaginationModule ]

## Install Dialog message

Run 'npm install sweetalert2'

In component where to call the dialog:
	import Swal from 'sweetalert2'
