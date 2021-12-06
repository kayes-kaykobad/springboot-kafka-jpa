import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { JobService } from "../job.service";


@Component({
  selector: 'app-job',
  templateUrl: './job.component.html',
  styleUrls: ['./job.component.css']
})
export class JobComponent implements OnInit {
  msg: string;
  msg2:string;
  msg3:string;
  inputJobId = new FormControl('', Validators.required);
  jobList: any;

  constructor(private jobService: JobService) {
    this.msg = '';
    this.msg2 = '';
    this.msg3 = '';
  }

  ngOnInit(): void {
  }

  createJobEvent() {
    this.jobService.addJob().subscribe(
      (res) => {
        debugger;
        this.msg = "Created job sucessfully. Job id is: " + res
      },
      (error) => {
        console.log(error)
      }
    );
  }

  getJobStatusEvent() {

    if(this.inputJobId.invalid)
    {
      return;
    }
    this.jobService.getJobByID(this.inputJobId.value).subscribe(
      (res) => {
        
        this.msg2 = "The status of the job is: " + res.status
      },
      (error) => {
        
        console.log(error)
      }
    );
  }

  getAllJobsEvent() {
    this.jobService.getAllJobs().subscribe(
      (res) => {
        debugger;
        this.jobList = res
      },
      (error) => {
        console.log(error)
      }
    );
  }
}
