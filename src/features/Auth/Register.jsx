import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup'; 
import { useNavigate } from 'react-router-dom';
import { useAuthContext } from './AuthContextProvider';



const validationSchema = yup.object({
    email: yup.string()
        .required('Please type your email')
        .email('Please type a valid email address'),
    password: yup.string()
        .required('Please choose a password')
        .min(6, 'The password needs to be at least 6 characters long'),
    retypePassword: yup.string()
        .required('Please type your password again')
        .oneOf([yup.ref('password')], 'The passwords do not match'),
    firstName: yup.string().trim().required('What is your first name?'),
    lastName: yup.string().trim().required('What is your last name?'),
});



export function Register(){

    const {user, isAuthenticated, login, logout} = useAuthContext();
    const navigate = useNavigate();


    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(validationSchema)
    });

    function onSubmit(data) {
        //console.log(data);

        const user = {
            name: `${data.firstName} ${data.lastName}`,
            email: data.email,
            password: data.password
        };
        console.log(user.name);

        fetch('http://localhost:8080/users/addUser', {
            method: 'POST',
            headers:{
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        }).then(response => {
            if(!response.ok){
                throw new Error('User not added to db');
            }
            return response.json();
        }).then(data=>{
            login(data);
            console.log('Success:', data);
        }).catch(()=>{
            alert('You already have an account on this email.');
        });
    
    }

    if (isAuthenticated){
        return(
            <>
            <h1>Hello, {user?.name}</h1>
            <p>Welcome back! Check out our latest protein products that can help you reach your fitness goals faster. Don't miss our exclusive deals just for you.</p>
                <button onClick={() => navigate('/')}>Shop Now</button>
                <button onClick = {logout}>Logout</button>
            </>
        );
        
    }
   

    return (

       
        <form className="brandForm" onSubmit={handleSubmit(onSubmit)}>
          <h1 className="fullWidth">Register</h1>
          <label htmlFor="email">Email</label>
          <input type="email" id="email" {...register('email')} />
          {errors.email && <p className="errorMessage">{errors.email.message}</p>}
    
          <label htmlFor="password">Password</label>
          <input type="password" id="password" {...register('password')} />
          {errors.password && <p className="errorMessage">{errors.password.message}</p>}

          <label htmlFor="retypePassword">Retype Password</label>
          <input type="password" id="retypePassword" {...register('retypePassword')} />
        {errors.retypePassword && <p className="errorMessage">{errors.retypePassword.message}</p>}
    
           <label htmlFor="firstName">First Name</label>
           <input type="text" id="firstName" {...register('firstName')} />
              {errors.firstName && <p className="errorMessage">{errors.firstName.message}</p>}
    
           <label htmlFor="lastName">Last Name</label>
            <input type="text" id="lastName" {...register('lastName')} />
            {errors.lastName && <p className="errorMessage">{errors.lastName.message}</p>}
    
          <button type="submit" className="btn secondColumn">Register
          </button>
        </form>
      );
    
}