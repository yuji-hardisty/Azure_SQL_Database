-- Create customer table
create table customer
(
    cname varchar(64) primary key,
    address varchar(64),
    category int
)
-- set cname as secondary index key
create index idx_cname
on customer (cname)

-- Create department table
create table department
(
    dept_number int primary key,
    dept_data varchar(128)
)

-- Create assembly table
create table assembly
(
    assembly_id int primary key,
    date_odered date,
    assembly_detail varchar(128),
    cname varchar(64) foreign key references customer(cname)
)

-- Create process table
create table process
(
    process_id int primary key,
    process_data varchar(64),
    process_type varchar(64),
    dept_number int foreign key references department(dept_number),
    assembly_id int foreign key references assembly(assembly_id)   
)

-- Create paint table
create table paint
(
    process_id int primary key,
    process_data varchar(64),
    dept_number int foreign key references department(dept_number),
    assembly_id int foreign key references assembly(assembly_id),  
    paint_type varchar(64),
    painting_method varchar(64)
)

-- Create fit table
create table fit
(
    process_id int primary key,
    process_data varchar(64),
    dept_number int foreign key references department(dept_number),
    assembly_id int foreign key references assembly(assembly_id),  
    fit_type varchar(64)
)

-- Create cut table
create table cut
(
    process_id int primary key,
    process_data varchar(64),
    dept_number int foreign key references department(dept_number),
    assembly_id int foreign key references assembly(assembly_id),  
    cutting_type varchar(64),
    machine_type varchar(64)
)

-- Create job table
create table job
(
    job_no int primary key,
    date_commenced date,
    date_completed date,
    job_type varchar(64),
    dept_number int foreign key references department(dept_number),
    assembly_id int foreign key references assembly(assembly_id)   
)

-- Create cut_job table
create table cut_job
(
    job_no int primary key,
    date_commenced date,
    date_completed date,
    type_of_machine varchar(64), 
    time_machine_used int,
    material_used varchar(64), 
    labor_time_c int,
    dept_number int foreign key references department(dept_number),
    assembly_id int foreign key references assembly(assembly_id)   
)

-- Create paint_job table
create table paint_job
(
    job_no int primary key,
    date_commenced date,
    date_completed date,
    color varchar(128), 
    volume int,     
    labor_time_p int,
    dept_number int foreign key references department(dept_number),
    assembly_id int foreign key references assembly(assembly_id)   
)

-- Create fit_job table
create table fit_job
(
    job_no int primary key,
    date_commenced date,
    date_completed date,
    labor_time_f int,
    dept_number int foreign key references department(dept_number),
    assembly_id int foreign key references assembly(assembly_id)   
)

-- Create is_assigned table
create table is_assigned
(
    job_no int primary key, 
    assembly_id int foreign key references assembly(assembly_id),
    process_id int foreign key references process(process_id), 
    dept_number int foreign key references department(dept_number) 
)

-- Create account table
create table account
(
    account_number int primary key,
    date_established date,
    account_type varchar(64)
)

-- Create assembly_account table
create table assembly_account
(
    account_number int primary key,
    date_established date,
    cost_a int,
    assembly_id int foreign key references assembly(assembly_id) 
)

-- Create dept_account table
create table dept_account
(
    account_number int primary key,
    date_established date,
    cost_d int,
    dept_number int foreign key references department(dept_number) 
)

-- Create process_account table
create table process_account
(
    account_number int primary key,
    date_established date,
    cost_p int,
    process_id int foreign key references process(process_id) 
)


-- Create transaction table
create table transaction_
(
    transaction_number int primary key,
    sup_cost int,
    account_number int foreign key references account(account_number) 
)

go

-- query 1: enter a new customer
create procedure insert_new_customer
@cname varchar(64),
@address varchar(64),
@category  int

as
begin


    insert into customer(cname, address, category)
    values (@cname, @address, @category)

end

go

-- query 2: enter a new department
create procedure insert_new_department
@dept_number int,
@dept_data varchar(128)

as
begin
    insert into department(dept_number, dept_data)
    values (@dept_number, @dept_data)
end

go

-- query 3: enter a new assembly
create procedure insert_new_assembly
@assembly_id int,
@date_ordered  date,
@assembly_detail varchar(128),
@cname varchar(64)

as
begin
    insert into assembly(assembly_id, date_odered, assembly_detail, cname)
    values (@assembly_id, @date_ordered, @assembly_detail, @cname)
end

go

-- query 4: enter a new process_id and its department
create procedure insert_new_process
@process_id int,
@process_data varchar(64),
@dept_number int,
@assembly_id int,
@process_type varchar(64),
@paint_type varchar(64), -- when process type is paint
@painting_method varchar(64),
@fit_type varchar(64), -- when process type is fit
@cutting_type varchar(64), -- when process type is cut
@machine_type varchar(64)

as
begin
    insert into process(process_id, process_data, dept_number, assembly_id, process_type)
    values (@process_id, @process_data, @dept_number, @assembly_id, @process_type)

        if @process_type = 'paint'
        insert into paint(process_id, process_data, dept_number, assembly_id, paint_type, painting_method)  
        values (@process_id, @process_data, @dept_number, @assembly_id, @paint_type, @painting_method) 
        
        else if @process_type = 'fit'
        insert into fit(process_id, process_data, dept_number, assembly_id, fit_type)
        values (@process_id, @process_data, @dept_number, @assembly_id, @fit_type)
        
        else if @process_type = 'cut'
        insert into cut(process_id, process_data, dept_number, assembly_id, cutting_type, machine_type)     
        values (@process_id, @process_data, @dept_number, @assembly_id, @cutting_type, @machine_type)     
        
end

go

-- query 5: enter a new account
create procedure insert_new_account
@account_number int,
@date_established date,
@account_type varchar(64),  
@assembly_id int, -- when account type is assembly_account
@dept_number int,  -- when account type is dept_account   
@process_id int -- when account type is process_account

as
begin
    insert into account(account_number, date_established, account_type)
    values (@account_number, @date_established, @account_type)

    if @account_type = 'assembly'
    insert into assembly_account(account_number, date_established, assembly_id, cost_a)    
    values (@account_number, @date_established, @assembly_id, 0)    

    if @account_type = 'department'
    insert into dept_account(account_number, date_established, dept_number, cost_d)  
    values (@account_number, @date_established, @dept_number, 0)    

    if @account_type = 'process'
    insert into process_account(account_number, date_established, process_id, cost_p)     
    values (@account_number, @date_established, @process_id, 0)           

end

go

-- query 6: enter a new job
create procedure insert_new_job
@job_no int,
@date_commenced date,
@dept_number int,
@assembly_id int,
@process_id int

as
begin
    insert into job(job_no, date_commenced, dept_number, assembly_id)
    values (@job_no, @date_commenced, @dept_number,@assembly_id)

    insert into is_assigned(job_no, assembly_id, process_id, dept_number)
    values (@job_no, @assembly_id, @process_id, @dept_number)

end

go



-- query 7: enter a completion of a job and job-info
create procedure insert_job_info
@job_no int,
@date_completed date,
@job_type varchar(64),
@type_of_machine varchar(128),  -- when job type is cut-job
@time_machine_used int,
@material_used varchar(128), 
@labor_time_c int,
@color varchar(128),  -- when job type is paint-job
@volume int,     
@labor_time_p int,
@labor_time_f int  -- when job type is fit-job

as
begin
    declare @assembly_id int
    declare @process_id int

    update job
    set date_completed = @date_completed, job_type=@job_type       -- insert date_completed where job_no = @job_no
    where job_no = @job_no

    if @job_type = 'cut'
    insert into cut_job(job_no, date_completed, type_of_machine, time_machine_used, material_used, labor_time_c)  -- insert date_completed and job-info with respect to job-type
    values (@job_no, @date_completed, @type_of_machine, @time_machine_used, @material_used, @labor_time_c)  

    if @job_type = 'cut'
    update cut_job
    set cut_job.date_commenced = job.date_commenced, cut_job.dept_number = job.dept_number, cut_job.assembly_id= job.assembly_id
    from job
    where job.job_no = cut_job.job_no  and job_type = 'cut'

    if @job_type = 'paint'
    insert into paint_job(job_no, date_completed, color, volume, labor_time_p)  -- insert date_completed and job-info with respect to job-type
    values (@job_no, @date_completed, @color, @volume, @labor_time_c) 

    if @job_type = 'paint'
    update paint_job
    set paint_job.date_commenced = job.date_commenced, paint_job.dept_number = job.dept_number, paint_job.assembly_id= job.assembly_id
    from job
    where job.job_no = paint_job.job_no  and job_type = 'paint'

  
    if @job_type = 'fit'
    insert into fit_job(job_no, date_completed, labor_time_f)  -- insert date_completed and job-info with respect to job-type
    values (@job_no, @date_completed, @labor_time_f)   

    if @job_type = 'fit'
    update fit_job
    set fit_job.date_commenced = job.date_commenced, fit_job.dept_number = job.dept_number, fit_job.assembly_id= job.assembly_id
    from job
    where job.job_no = fit_job.job_no  and job_type = 'fit'
         

end

go

-- query 8: enter a new transaction and update the cost of equivalent account
create procedure insert_new_transaction
@transaction_number int,
@sup_cost int,
@account_number int -- from account, and each account

as
begin
    insert into transaction_(transaction_number, sup_cost, account_number)
    values (@transaction_number, @sup_cost, @account_number)

    update assembly_account                       -- update cost of assembly_account
    set cost_a = cost_a + @sup_cost
    where account_number =  @account_number

    update dept_account                       -- update cost of dept_account
    set cost_d = cost_d + @sup_cost
    where account_number =  @account_number

    update process_account                       -- update cost of process_account
    set cost_p = cost_p + @sup_cost
    where account_number =  @account_number   
       
end

go

-- query 9: retrieve the cost incurred on an assembly-id
create procedure find_cost_with_assembly_id
@assembly_id int

as
begin
    select cost_a
    from assembly_account
    where assembly_id = @assembly_id   
end


go

--drop procedure find_labor_time
-- query 10: retrieve the total labor time within a department for jobs completed in the department during
-- a given date
create procedure find_labor_time
@given_date date,
@given_dept_number int

 -- total labor time = labor_time_c + labor_time_p + labor_time_f
as
begin
    select  isNULL(c.sum_c,0) + isNULL(p.sum_p,0) + isNuLL(f.sum_f,0)
    from
    (select sum(labor_time_c) as sum_c
    from cut_job
    where date_completed = @given_date and dept_number = @given_dept_number
    ) as c, 
    (select sum(labor_time_p) as sum_p
    from paint_job
    where date_completed = @given_date and dept_number = @given_dept_number
    ) as p,
    (select sum(labor_time_f) as sum_f
    from fit_job
    where date_completed = @given_date and dept_number = @given_dept_number
    ) as f

end




go

-- query 11: Retrieve the processes through which a given assembly-id has passed so far
-- (in datecommenced order) and the department responsible for each process
create procedure find_processes
@assembly_id int

as
begin
    select process_id, is_assigned.dept_number
    from is_assigned, job
    where is_assigned.assembly_id = @assembly_id and job.job_no = is_assigned.job_no
    order by job.date_commenced

end

go


-- query 12: Retrieve the jobs (together with their type information and assembly-id) 
-- completed during a given date in a given department
create procedure find_jobs_completed
@given_date date,
@given_dept_number int

as
begin
    select distinct job.job_no, job.job_type, is_assigned.assembly_id 
    from is_assigned, job
    where job.date_completed = @given_date and is_assigned.dept_number = @given_dept_number

end

go



-- query 13: Retrieve the customers (in name order) whose category is in a given range
create procedure find_customers_with_category_range
@category_from int,
@category_to int

as
begin

    select *
    from customer
    where category >= @category_from and category <= @category_to
    order by cname
   
end

go


-- query 14: Delete all cut-jobs whose job-no is in a given range 
create procedure delete_cut_jobs_with_range
@job_no_from int,
@job_no_to int

as
begin
    delete from cut_job
    where job_no >= @job_no_from and job_no <= @job_no_to
    delete from job
    where job_no >= @job_no_from and job_no <= @job_no_to and job_type = 'cut' -- do the same with job table

end

go

-- query 15: Change the color of a given paint job
create procedure change_color_of_paint_job
@job_no int,
@color_new varchar(128)

as
begin
    update paint_job
    set color = @color_new
    where job_no = @job_no
end

